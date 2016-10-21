'use strict';

angular.module('nbadraftApp')
    .controller('PlayerController', function ($scope, $state, Player) {
        $scope.showTaken = false;
        $scope.players = [];
        $scope.allPlayers = [];
        $scope.loadAll = function() {
            Player.query(function(result) {
                result.sort(function(a,b) {
                    return b.value > a.value;
                });
                 $scope.allPlayers = result;
               var res1 = [];
                for (var i = 0; i < result.length; i++) {
                    if (result[i].team === null || result[i].team === undefined) {
                        res1.push(result[i]);
                    }
                }
               $scope.players = res1;
            });
        };
        $scope.loadAll();

        $scope.showTakenF = function(){
            $scope.showTaken = true;
            $scope.filterAgain();
        };
        $scope.hideTaken = function(){
             $scope.showTaken = false;
             $scope.filterAgain();
        };

         $scope.playerTaken = function (player) {
             if (player.team!== null && player.team!==undefined) {
                       player.team=undefined;
       } else {
   
               player.team="ateam";
       }
           Player.update(player);
           $scope.filterAgain();
        };
        $scope.isChecked = function (player) {
           if (player.team!== null && player.team!==undefined) {
            return true;
           }
           return false;
        };
        $scope.filterAgain = function(){
             var res1 = [];
             if (!$scope.showTaken) {
                for (var i = 0; i < $scope.allPlayers.length; i++) {
                    if ($scope.allPlayers[i].team === null || $scope.allPlayers[i].team === undefined) {
                        res1.push($scope.allPlayers[i]);
                    }
                }
        
                $scope.players =res1;
            } else {
                 $scope.players =$scope.allPlayers;
            }
        };
        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.player = {
                name: null,
                threePointsMade: null,
                threePointsPct: null,
                assists: null,
                blocks: null,
                freeThrowMade: null,
                freeThrowPct: null,
                fieldGoalsPct: null,
                gamesPlayed: null,
                minutes: null,
                points: null,
                rebounds: null,
                steals: null,
                turnovers: null,
                value: null,
                hasBoth: null,
                id: null
            };
        };
    });
