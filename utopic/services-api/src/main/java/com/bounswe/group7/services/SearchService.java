/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.services;

import com.bounswe.group7.dbpedia.WikidataIntegration;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.wikidata.WikiResults;
import com.bounswe.group7.repository.TopicsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ugurbor
 */
@Service
public class SearchService {

    private static final HashSet<String> stopWords = new HashSet<String>(Arrays.asList("a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"));

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private UsersService usersService;

    public List<Topics> searchTopics(String keywords) {
        String[] keywordArr = keywords.toLowerCase().split(" ");
        List<String> keywordListTemp = Arrays.asList(keywordArr);
        ArrayList<String> keywordList = new ArrayList<String>();
        keywordList.addAll(keywordListTemp);
        if (keywordArr.length > 1) {
            keywordList.add(keywords);
        }
        keywordList.removeAll(stopWords);
        ConcurrentHashMap<String, String> keywordMap = new ConcurrentHashMap<>();

        keywordList.parallelStream().forEach((key) -> {
            WikiResults wikiRes = WikidataIntegration.search(key);
            if (wikiRes != null) {
                wikiRes.getSearch().parallelStream().forEach((word) -> {
                    if (word != null && word.getDescription() != null) {
                        Arrays.asList(word.getDescription().split(" ")).parallelStream().forEach((bok) -> {
                            if (bok != null) {
                                if (!stopWords.contains(bok)) {
                                    keywordMap.put(bok, bok);
                                }
                            }
                        });
                    }
                });
            }
        });

        ConcurrentHashMap<Long, Topics> searchResults = new ConcurrentHashMap<>();
        ConcurrentHashMap<Long, Integer> idWithFreq = new ConcurrentHashMap<Long, Integer>();

        for (String s : keywordList) {
            keywordMap.put(s, s);
        }
        keywordMap.values().parallelStream().forEach((key) -> {
            topicsRepository.findByTags(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 7);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseHeaderContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 5);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByTopicPack(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 4);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseDescriptionContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getDescription(), key) * 2);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseContentContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getContent(), key));
            });
        });

        ArrayList<Pair<Integer, Topics>> listTest = new ArrayList<>();
        for (Entry entry : idWithFreq.entrySet()) {
            listTest.add(new Pair((Integer) entry.getValue(), searchResults.get(entry.getKey())));
        }

        Collections.sort(listTest, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return (int) ((Integer) b.getKey() - (Integer) a.getKey());
            }
        });
        ArrayList<Topics> res = new ArrayList<Topics>();

        for (Pair p : listTest) {
            res.add((Topics) p.getValue());
        }
        return res;
    }

    public List<Topics> topicRecommendations(Long topicId) {

        Topics theTopic = topicsService.getTopic(topicId);
        TopicPacks thePack = topicsService.getTopicPack(theTopic.getTopicPackId());
        String keywords = theTopic.getHeader().toLowerCase() + " " + thePack.getName().toLowerCase();
        for (Tags tag : theTopic.getTags()) {
            keywords += " " + tag.getLabel().toLowerCase() + " " + tag.getCategory().toLowerCase();
        }
        String[] keywordArr = keywords.split(" ");
        List<String> keywordListTemp = Arrays.asList(keywordArr);
        ArrayList<String> keywordList = new ArrayList<String>();
        keywordList.addAll(keywordListTemp);
        if (keywordArr.length > 1) {
            keywordList.add(keywords);
        }
        keywordList.removeAll(stopWords);

        ConcurrentHashMap<Long, Topics> searchResults = new ConcurrentHashMap<>();
        ConcurrentHashMap<Long, Integer> idWithFreq = new ConcurrentHashMap<Long, Integer>();

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByTags(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 7);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseHeaderContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 5);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByTopicPack(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 4);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseDescriptionContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getDescription(), key) * 2);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseContentContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getContent(), key));
            });
        });

        searchResults.remove(topicId);
        idWithFreq.remove(topicId);

        ArrayList<Pair<Integer, Topics>> listTest = new ArrayList<>();
        for (Entry entry : idWithFreq.entrySet()) {
            listTest.add(new Pair((Integer) entry.getValue(), searchResults.get(entry.getKey())));
        }

        Collections.sort(listTest, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return (int) ((Integer) b.getKey() - (Integer) a.getKey());
            }
        });
        ArrayList<Topics> res = new ArrayList<Topics>();

        for (Pair p : listTest) {
            res.add((Topics) p.getValue());
        }
        return res;
    }

    public List<Topics> userRecommendations() {
        String keywords = "";
        List<Topics> followed = topicsService.getUserFollowedTopics();
        for (Topics theTopic : followed) {
            TopicPacks thePack = topicsService.getTopicPack(theTopic.getTopicPackId());
            keywords += theTopic.getHeader().toLowerCase() + " " + thePack.getName().toLowerCase();
            for (Tags tag : theTopic.getTags()) {
                keywords += " " + tag.getLabel().toLowerCase() + " " + tag.getCategory().toLowerCase();
            }
            keywords += " ";
        }
        String[] keywordArr = keywords.split(" ");
        List<String> keywordListTemp = Arrays.asList(keywordArr);
        ArrayList<String> keywordList = new ArrayList<String>();
        keywordList.addAll(keywordListTemp);
        if (keywordArr.length > 1) {
            keywordList.add(keywords);
        }
        keywordList.removeAll(stopWords);

        ConcurrentHashMap<Long, Topics> searchResults = new ConcurrentHashMap<>();
        ConcurrentHashMap<Long, Integer> idWithFreq = new ConcurrentHashMap<Long, Integer>();

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByTags(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 7);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseHeaderContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 5);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByTopicPack(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + 4);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseDescriptionContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getDescription(), key) * 2);
            });
        });

        keywordList.parallelStream().forEach((key) -> {
            topicsRepository.findByIgnoreCaseContentContaining(key).parallelStream().forEach((topic) -> {
                searchResults.put(topic.getTopicId(), topic);
                idWithFreq.putIfAbsent(topic.getTopicId(), 0);
                idWithFreq.replace(topic.getTopicId(), idWithFreq.get(topic.getTopicId()) + StringUtils.countMatches(topic.getContent(), key));
            });
        });

        for (Topics topic : followed) {
            idWithFreq.remove(topic.getTopicId());
            searchResults.remove(topic.getTopicId());
        }

        ArrayList<Pair<Integer, Topics>> listTest = new ArrayList<>();
        for (Entry entry : idWithFreq.entrySet()) {
            listTest.add(new Pair((Integer) entry.getValue(), searchResults.get(entry.getKey())));
        }

        Collections.sort(listTest, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return (int) ((Integer) b.getKey() - (Integer) a.getKey());
            }
        });
        ArrayList<Topics> res = new ArrayList<Topics>();

        for (Pair p : listTest) {
            res.add((Topics) p.getValue());
        }
        return res;
    }

    public static class Pair<K, V> {

        private final K key;
        private final V value;

        public static <K, V> Pair<K, V> createPair(K element0, V element1) {
            return new Pair<K, V>(element0, element1);
        }

        public Pair(K element0, V element1) {
            this.key = element0;
            this.value = element1;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }

}
