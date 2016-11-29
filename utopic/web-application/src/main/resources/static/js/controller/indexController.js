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
  questionNum = 0;
  
  $scope.addTag = function(){
    console.log($scope.tagSelected);
    console.log($scope.tags);
    for(var i = 0; i < $scope.tags.length; i++){
        console.log($scope.tags[i].id + "-"+ $scope.tagSelected.id);
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
    console.log($scope.questions);
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
      var data = {"content": $scope.htmlContent, "header": $scope.titleInput, "tags": $scope.tags, "description": $scope.description, "questions": $scope.questions};
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/createTopic",
            data: JSON.stringify(data),
        }).done(function(data)
        {
            console.log(data);
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
  
  /*$scope.inputChanged = function(changed){
      console.log(changed);
      $.getJSON('https://www.wikidata.org/w/api.php?action=wbsearchentities&search=' +  changed + '&language=en&format=json&callback=?', function(data){
        $scope.tagList = data.search;
        for(var i = 0; i < $scope.tagList.length; i++){
            $scope.tagList[i].text = $scope.tagList[i].label + " (" + $scope.tagList[i].description + ")";
        }
        $scope.$digest(
       
        console.log($scope.tagList);
      });
  };*/
 
    $scope.selectTag = function(tag){
        $scope.tagSelected = tag;
        $scope.tagInput = tag.text;
    };
  
    $scope.$watch('tagInput', function(){
        $.getJSON('https://www.wikidata.org/w/api.php?action=wbsearchentities&search=' +  $scope.tagInput + '&language=en&format=json&callback=?', function(data){
          $scope.tagList = data.search.slice(0,10);
          /*for(var i = 0; i < $scope.tags.length; i++){
              for(var j = 0; j < $scope.tagList.length; j++){
                  if($scope.tags[i].id == $scope.tagList[j].id)
                      $scope.tagList.splice(j,1);
              }
          }*/
          for(var i = 0; i < $scope.tagList.length; i++){
              $scope.tagList[i].text = $scope.tagList[i].label + " (" + $scope.tagList[i].description + ")";
          }
          $scope.$digest();

          console.log($scope.tagList);
        });
    });
});

