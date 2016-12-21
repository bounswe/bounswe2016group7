mainModel.controller('userController',function indexController($scope) {
    $scope.reviews = reviews;
    $scope.topics = topics;
    $scope.reviewToAdd = '';
    $scope.profiledAuths = profiledUserAuth;
    $scope.profilePicture = '/images/user.png';
    $scope.currentUserId = activeId;
    $scope.ownerId = ownerId;
    $scope.association = "Not entered";
    $scope.newAssociation = "";
    $scope.displayAssocInput = false;
    $scope.bio = "Not entered";
    $scope.newBio = "";
    $scope.displayBioInput = false;
    
    if(association){
        $scope.association = association;
    }
    if(bio){
        $scope.bio = bio;
    }
    var scrollToBottom = function(selector){
        $(selector).scrollTop($(selector)[0].scrollHeight);
    };
    $scope.showBioEdit = function(){
        $scope.displayBioInput = true;
    }
    
    $scope.showAssociationEdit = function(){
        $scope.displayAssocInput = true;
    }
    
    $scope.changeBio = function(newBio){
        console.log(newBio);
        if(!newBio){
            $scope.displayBioInput = false;
            return;
        }
        
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/setbio",
            data: newBio,
        }).done(function(data) {
            $scope.bio = data.responseText;
            $scope.displayBioInput = false;
        }).fail(function(data){
            if(data.status == 200){
                $scope.bio = data.responseText;
                $scope.displayBioInput = false;
            }
            $scope.$digest();
        });
    }
    
    $scope.changeAssociation = function(){
        if(!$scope.newAssociation){
            $scope.displayAssocInput = false;
            return;
        }
        
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/setassociation",
            data: $scope.newAssociation,
        }).done(function(data) {
            console.log(data);
            $scope.association = data.responseText;
            $scope.displayAssocInput = false;
        }).fail(function(data){
            console.log(data);
            if(data.status == 200){
                $scope.association = data.responseText;
                $scope.displayAssocInput = false;
            }
            $scope.$digest();
        });
    }
    
    //function sends commnet data do back-end
    $scope.addReview = function(reviewToAdd){
        content = reviewToAdd;
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
        }).fail(function(data){
            console.log(data);
        });
    });
});

