 'use strict';

angular.module('nbadraftApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-nbadraftApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-nbadraftApp-params')});
                }
                return response;
            }
        };
    });
