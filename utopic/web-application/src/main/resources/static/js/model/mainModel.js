var mainModel = angular.module('mainApp',['valdr','textAngular',"angucomplete-alt"]);
mainModel.filter('to_trusted', ['$sce', function($sce){
    return function(text) {
        return $sce.trustAsHtml(text);
    };
}]);
mainModel.factory('passwordMatch', function () {
  return {
    name: 'passwordMatch',
    validate: function (value, params) {
      var element = document.getElementById(params.matchField);
      console.log(element.innerHTML);
      return element.innerHTML == value;
    }
  };
});


mainModel.config(function(valdrProvider) {
  valdrProvider.addValidator('passwordMatch');
  valdrProvider.addConstraints({
    'Login': {
      'password': {
        'size': {
          'min': 6,
          'max': 20,
          'message': 'Password must be between 6 and 20 characters.'
        },
        'required': {
          'message': 'Password is required.'
        }
      },
      'username': {
        'required': {
          'message': 'This field is required.'
        }
      }
    },
		'Register': {
      'password': {
        'size': {
          'min': 6,
          'max': 20,
          'message': 'Password must be between 6 and 20 characters.'
        },
        'required': {
          'message': 'Password is required.'
        }
      },
			'email': {
        'email': {
          'message': 'Valid e-mail address is required.'
        },
        'required': {
          'message': 'Email is required.'
        }
      },
      'username': {
        'required': {
          'message': 'This field is required.'
        }
      },
      'repassword': {
        'passwordMatch':{
          'message': 'passwords does not match',
          'field':'user.repassword',
          'matchField':'selam'
        }
      }
    }
  })
});