'use strict';

angular.module('nbadraftApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


