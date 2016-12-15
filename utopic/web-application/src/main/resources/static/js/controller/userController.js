mainModel.controller('userController',function indexController($scope) {
    $scope.reviews = reviews;
    $scope.topics = topics;
    $scope.reviewToAdd = '';
    $scope.profiledAuths = profiledUserAuth;
    $scope.profilePicture = '/images/user.png';
    $scope.currentUserId = activeId;
    $scope.ownerId = ownerId;
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
    };
    
    $(document).on('change','#update-picture',function(){
        var formData = new FormData($('#picture-form')[0]);
        var selam = '';
        console.log(formData);
        $.ajax({
            url: '/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false
        }).done(function(data) {
            $scope.profilePicture = data.responseText;
        }).fail(function(data){
            if(data.status == 200){
                $scope.profilePicture = data.responseText;
            }
        });
        $scope.$digest();
    });
    var imageUrl = "http://localhost:8090/images/user-";
    $(document).ready(function(){
        
        $.ajax({
            url: imageUrl + ownerId + '.jpg',
            type: 'GET'
        }).done(function(data) {
            $scope.profilePicture = imageUrl + ownerId + '.jpg';
            $scope.$digest();
        }).fail(function(data){
            console.log(data);
        });
    });
});

