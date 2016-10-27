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
    },
		'Register': {
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
      }
    }
  })
});