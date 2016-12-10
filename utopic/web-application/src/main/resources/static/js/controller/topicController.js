mainModel.controller('topicController',function indexController($scope) {
    $scope.comments = comments;
    $scope.commentToAdd = '';
    $scope.repliedId = '';
    $scope.tags = topicTags;
    $scope.quiz = quiz.questionList;
    $scope.topicOwner = ownerId;
    $scope.activeUser = activeId;
    $scope.followText = '';
    $scope.followingUsers = [];
    $scope.showFollowing = false;
    $scope.currentRate = initialRating;
    
    var size = $scope.quiz.length;
    for(var i = 0; i< size; i++){
        for(var j = 0; j<$scope.quiz[i].options.length; j++){
            var option = $scope.quiz[i].options[j];
            var index = $scope.quiz[i].options.indexOf(option)
            $scope.quiz[i].options[j].number = j;
            if(option.text=='Default_Choice'){
                $scope.quiz[i].options.splice(index,1);
                j--;
            }
        }
    }
    
    var scrollToBottom = function(selector){
        $(selector).scrollTop($(selector)[0].scrollHeight);
    };
    
    var getContentWithReply = function(content){
        if(content[0] != '@')
            return content;
        var replyUsername = '';
        var i = 0;
        for(i; i < content.length; i++){
            if(content[i] == ':')
                break;
            replyUsername += content[i];
        }
        return '<a class="show-replied" targetComment="#'+ $scope.repliedId +'" href="#">'+ replyUsername +'</a> ' + content.substring(i+1);
    };
    
    //function sends commnet data do back-end
    $scope.addComment = function(){
        content = getContentWithReply($scope.commentToAdd);
        var data = {"topicId": topicId,"userId": ownerId, "text": content};
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/addcomment",
            data: JSON.stringify(data)
        }).done(function(data) {
            $scope.$apply(function (){
               $scope.comments = data;
               $scope.commentToAdd = "";
            });
            scrollToBottom('.message-container');
        }).fail(function(data){
            console.log(data);
        });
    };
    
    //reply functionality
    $scope.reply = function($event){
        var dom = $event.target;
        if($(dom).hasClass("fa"))
            dom = $(dom).parent("button")[0];
        var commentId = dom.getAttribute("slideid");
        $scope.repliedId = commentId;
        var username = dom.getAttribute("name");
        $scope.commentToAdd = '@'+ username + ' :';
    }
    
    $(document).on('click','.show-replied',function(){
        var target = $(this)[0].getAttribute("targetComment");
        var myElement = $(''+target)[0]
        var topPos = myElement.offsetTop;
        $('.message-container')[0].scrollTop = topPos;
        $('html,body').animate({
            scrollTop: $('.message-container').offset().top -100
         });
    });
    
    $scope.setAnswer = function(question, option){
        question.optionId = option.number;
        //console.log(quiz);
        $('#question'+question.id+' button').removeClass('selected');
        $('#question'+question.id+' .option'+option.number+' button').addClass('selected');
    };
    
    $scope.sendQuiz = function(){
        var answers = [];
        for(var i=0; i<$scope.quiz.length;i++){
            answers.push({"questionId":$scope.quiz[i].id, "optionId": $scope.quiz[i].optionId})
        };
        
        var data = {"quizId": quiz.quizId,"questionList": answers};
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/solvequiz",
            data: JSON.stringify(data)
        }).done(function(data) {
            //console.log(data);
        }).fail(function(data){
            //console.log(data);
        });
    };
    
    $scope.followTopic = function(){
        data = {'topicId': topicId};
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/followtopic",
            data: JSON.stringify(data)
        }).done(function(data) {
            if($scope.followText == 'UNFOLLOW'){
                $scope.followText = 'FOLLOW';
                for(var i = 0; i < $scope.followingUsers.length; i++){
                    if($scope.followingUsers[i].id == activeId){
                        $scope.followingUsers.splice(i+1,1);
                        break;
                    }
                }
            }
            else{
                $scope.followText = 'UNFOLLOW';
                $scope.followingUsers.push({"id":activeId,"username": activeUsername});
            }
            $scope.$digest();
        }).fail(function(data){
            console.log(data);
        });
    };
    
    var isTopicFollowed = function(){
        data = {'topicId': topicId};
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/istopicfollowed",
            data: JSON.stringify(data)
        }).done(function(data) {
            if(data == true)
                $scope.followText = 'UNFOLLOW';
            if(data == false)
                $scope.followText = 'FOLLOW';
            $scope.$digest();
        }).fail(function(data){
            console.log(data);
        });
    };
    
    $scope.toggleFollowing = function(){
        $scope.showFollowing = !$scope.showFollowing;
    };
    var updateRating = function(){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/gettopicrate/"+topicId
        }).done(function(data) {
            console.log(data)
            if(data != 0)
                $scope.currentRate = data;
            $scope.$digest();
        }).fail(function(data){
            console.log(data);
        });
    };
    $(document).ready(function(){
        isTopicFollowed();
        $scope.followingUsers = followingUsers;
        $scope.$digest();
        
        $(".topic-rating").starRating({
            initialRating: initialRating,
            callback: function(currentRating, $el){
                $.ajax({
                    type: "GET",
                    contentType: "application/json; charset=utf-8",
                    url: "/ratetopic/"+currentRating+"/"+topicId
                }).done(function(data) {
                    if(data)
                        updateRating();
                }).fail(function(data){
                    console.log(data);
                });
            },
            useFullStars: true,
            hoverColor: "#0a6c8e",
            useGradient: false,
            activeColor: "#fec400",
            starSize: 25,
            disableAfterRate: false
        });
    });
    
    
    
}); 

