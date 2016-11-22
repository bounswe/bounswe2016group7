mainModel.controller('indexController',function indexController($scope) {
  $scope.tagInput = '';
  $scope.titleInput = '';
  $scope.tags = [];
  $scope.description = '';
  $scope.htmlContent = '';
  $scope.questions = [];
  $scope.createdTopicId = 0;
  $scope.auths = auth;
  questionNum = 0;
  
  $scope.addTag = function(){
    $scope.tags.push($scope.tagInput);
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
      var data = {"content": $scope.htmlContent,"header": $scope.titleInput, "tags": $scope.tags, "description": $scope.descriptionInput, "questions": $scope.questions};
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/createTopic",
            data: JSON.stringify(data),
        }).done(function(data)
        {window.location.replace('/topic/'.concat(data))})
                .fail(function(data){
            console.log(data);
        });
  };
  
  var menuFlag = 0;
  $scope.toggleMenu = function(){
    if(menuFlag==0){
      $('ul.nav').slideDown();
      menuFlag = 1;
    }
    else{
      $('ul.nav').slideUp();
      menuFlag = 0;
    }
  };
	if(typeof recentTopics != 'undefined')
    $scope.recentTopics = recentTopics.slice(0,4);
	if(typeof topTopics != 'undefined')
    $scope.topTopics = topTopics.slice(0,4);
});

