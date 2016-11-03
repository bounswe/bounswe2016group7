mainModel.controller('indexController',function indexController($scope) {
  $scope.deneme = 'yaz lan';
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
});

