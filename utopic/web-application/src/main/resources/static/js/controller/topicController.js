mainModel.controller('topicController',function indexController($scope) {
    $scope.comments = comments;
    $scope.commentToAdd = '';
    $scope.repliedId = '';
    $scope.tags = topicTags;
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
        console.log(content);
        console.log($scope.repliedId);
        console.log(replyUsername);
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
            data: JSON.stringify(data),
        }).done(function(data) {
            $scope.$apply(function (){
               $scope.comments = data;
               $scope.commentToAdd = "";
            });
            scrollToBottom('.message-container');
        }).fail(function(data){
            console.log(data);
        });
        console.log(content);
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
    console.log($scope.tags);
});

