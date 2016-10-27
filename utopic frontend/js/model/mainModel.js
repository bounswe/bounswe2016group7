var mainModel = angular.module('mainApp',['valdr']);
mainModel.config(function(valdrProvider) {
  valdrProvider.addConstraints({
    'Login': {
      'password': {
        'size': {
          'min': 6,
          'max': 10,
          'message': 'Password must be between 6 and 10 characters.'
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
    }
  })
});