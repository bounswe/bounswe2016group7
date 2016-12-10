mainModel.controller('searchController',function indexController($scope) {
  $scope.topics = topics;
  $(document).ready(function(){
    for(var i = 0; i < $scope.topics.length; i++){
        console.log($("#topic-rating-"+$scope.topics[i].id));
        $("#topic-rating-"+$scope.topics[i].id).starRating({
              initialRating: $scope.topics[i].rate,
              hoverColor: "#0a6c8e",
              useGradient: false,
              activeColor: "#fec400",
              starSize: 25
          });
    }
  });
});

