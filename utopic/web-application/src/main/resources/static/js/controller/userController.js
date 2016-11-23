mainModel.controller('userController',function indexController($scope) {
    $scope.reviews = reviews;
    $scope.topics = topics;
    $scope.reviewToAdd = '';
    $scope.profiledAuths = profiledUserAuth;
    var scrollToBottom = function(selector){
        $(selector).scrollTop($(selector)[0].scrollHeight);
    };
    
    //function sends commnet data do back-end
    $scope.addReview = function(){
        content = $scope.reviewToAdd;
        var data = {"userId": ownerId,"reviewerId": activeId, "text": content}; //userId?
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/addreview",
            data: JSON.stringify(data),
        }).done(function(data) {
            $scope.$apply(function (){
               $scope.reviews = data;
               $scope.reviewToAdd = "";
            });
            scrollToBottom('.message-container');
        }).fail(function(data){
            console.log(data);
        });
        console.log(content);
    };
});

