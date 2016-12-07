mainModel.controller('indexController',function indexController($scope,$filter) {
  $scope.tagInput = '';
  $scope.titleInput = '';
  $scope.tags = [];
  $scope.jsonTags = [];
  $scope.description = '';
  $scope.htmlContent = '';
  $scope.questions = [];
  $scope.createdTopicId = 0;
  $scope.auths = auth;
  $scope.tagList = [];
  $scope.tagSelected = {};
  $scope.topicPacks = [];
  $scope.topicPackShow = [];
  $scope.topicPackSelected = {};
  
  var questionNum = 0;
    var getTopicPacks = function(){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/gettopicpacks",
        }).done(function(data)
        {
            $scope.topicPacks = data;
            console.log($scope.topicPacks);
        }).fail(function(data){
            console.log(data);
        });
    };
  $scope.addTag = function(){
    for(var i = 0; i < $scope.tags.length; i++){
        if($scope.tags[i].id == $scope.tagSelected.id){
            $scope.tagInput = "";
            return;
        }
    }
    if($scope.tagSelected.text)
        $scope.tags.push($scope.tagSelected);
    $scope.tagInput = "";
  };
  
  
  $scope.addQuestion = function(){
      $scope.questions.push({text:"",options:[]})
  };
  
  $scope.addOption = function(question){
    if(question.options.length < 4)
      question.options.push({text:"", isValid:0});
  };
  
  $scope.deleteOption = function(question, option){
    var index = question.options.indexOf(option);
    question.options.splice(index,1);
  };
  
  $scope.deleteQuestion = function(question){
    var index = $scope.questions.indexOf(question);
    $scope.questions.splice(index,1);
  };
  
  $scope.saveTopic = function(){
      var topicPackToSend = {};
      if(typeof $scope.topicPackSelected.topicPackName != 'undefined'){
          topicPackToSend = {
             topicPackName: $scope.topicPackSelected.topicPackName,
             topicPackId: $scope.topicPackSelected.topicPackId
          };
      }else if($scope.topicPackInput){
          topicPackToSend = {
              topicPackName : $scope.topicPackInput,
              topicPackId : -1
          };
      }else{
          topicPackToSend = {
              topicPackName : '',
              topicPackId : -1
          };
      }
      console.log($scope.tags );
      console.log($scope.questions);
      var data = {"topicPack": topicPackToSend ,"content": $scope.htmlContent, "header": $scope.titleInput, "tags": $scope.tags, "description": $scope.description, "questions": $scope.questions};
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/createTopic",
            data: JSON.stringify(data),
        }).done(function(data)
        {
            if(data)
                window.location.replace('/topic/'.concat(data))
        }).fail(function(data){
            console.log(data);
        });
  };
  
  var menuFlag = 0;
  $scope.toggleMenu = function(){
    if(menuFlag==0){
      $('.navbar-dark ul.nav').slideDown();
      menuFlag = 1;
    }
    else{
      $('.navbar-dark ul.nav').slideUp();
      menuFlag = 0;
    }
  };
  if(typeof recentTopics != 'undefined')
    $scope.recentTopics = recentTopics.slice(0,4);
  if(typeof topTopics != 'undefined')
    $scope.topTopics = topTopics.slice(0,4);
 
    $scope.selectTag = function(tag){
        $scope.tagSelected = tag;
        $scope.tagInput = tag.text;
    };
  
    $scope.$watch('tagInput', function(){
        if(!$scope.tagInput)
            return;
        $.getJSON('https://www.wikidata.org/w/api.php?action=wbsearchentities&search=' +  $scope.tagInput + '&language=en&format=json&callback=?&limit=10', function(data){
          if($scope.tagList)
            $scope.tagList = data.search.slice(0,10);
          /*for(var i = 0; i < $scope.tags.length; i++){
              for(var j = 0; j < $scope.tagList.length; j++){
                  if($scope.tags[i].id == $scope.tagList[j].id)
                      $scope.tagList.splice(j,1);
              }
          }*/
          for(var i = 0; i < $scope.tagList.length; i++){
              $scope.tagList[i].text = $scope.tagList[i].label + " (" + $scope.tagList[i].description + ")";
              $scope.tagList[i].category = $scope.tagList[i].description;
          }
          $scope.$digest();
        });
    });
    
  getTopicPacks();
 
  $scope.$watch('topicPackInput',function(){
      $scope.topicPackShow = [];
      if($scope.topicPackInput == '')
          return;
      for(var i = 0; i < $scope.topicPacks.length; i++){
          if($scope.topicPacks[i].topicPackName.toLowerCase().indexOf($scope.topicPackInput.toLowerCase()) > -1)
              $scope.topicPackShow.push($scope.topicPacks[i]);
      }
      if($scope.topicPackShow.length == 1 && $scope.topicPackShow[0] == $scope.topicPackSelected && $scope.topicPackInput == $scope.topicPackSelected.topicPackName)
          $scope.topicPackShow = [];
  });
  
  $scope.selectTopicPack = function(topicPack){
      $scope.topicPackInput = topicPack.topicPackName;
      $scope.topicPackSelected = topicPack;
  };
});

