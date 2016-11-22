<%-- 
    Document   : creatorModal
    Created on : Nov 21, 2016, 7:12:21 PM
    Author     : necil
--%>

<div style='text-align:left;' ng-controller="indexController" class="remodal" data-remodal-id="create-topic-modal">
    <button  ng-if="auths.indexOf('ROLE_CREATOR')!=-1" data-remodal-action="close" class="remodal-close"></button>
    <br/>
        <input ng-model="titleInput" name="title" id="title" class="form-control" type="text" placeholder="Title" ng-model="createTopic.title"/>
        <div class="row">
            <div class="col-xs-10">
                <input ng-model="tagInput" name="tag" id="add-tag" class="form-control" type="text" placeholder="Add a Tag"/>
            </div>
            <div class="col-xs-2">
                <button type="button" ng-click="addTag()"  class="button button-orange">ADD</button>
            </div>
        </div>
        <input ng-model="descriptionInput" name="description" id="description" class="form-control" type="text" placeholder="Description" ng-model="createTopic.description"/>
        <div class="tags margin-bottom">
            <div ng-repeat="tag in tags" class="topic-tag">{{tag}}</div>
        </div>
        <div class="material-container margin-bottom">
            <div text-angular ta-toolbar="[['h4', 'p', 'ul', 'insertImage','insertLink', 'insertVideo']]" ng-model="htmlContent"></div>
        </div>
        <div class="questions-container">
            <div class="question" ng-repeat="question in questions">
                <h5>Question {{$index+1}}</h5>
                <div class="row">
                    <div class="col-xs-7">
                        <input type="text" class="form-control" placeholder="Question" ng-model="question.text"/>
                    </div>
                    <div class="col-xs-5">
                        <button type="button" class="button button-orange" ng-click="addOption(question)">ADD OPTION</button>
                        <button class="button button-red" ng-click="deleteQuestion(question)">x</button>
                    </div>
                </div>
                <table ng-show="question.options.length" class="table question-create-table">
                    <thead>
                        <tr>
                            <th>
                                Option
                            </th>
                            <th>
                                Is Correct
                            </th>
                            <th>
                                Delete
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="option in question.options">
                            <td>
                                <input class="form-control" type="text" placeholder="Option {{$index+1}}" ng-model="option.text"/>
                            </td>
                            <td>
                                <input type="radio" id="opt{{$parent.$index}}-{{$index}}" name="opt{{$parent.$index}}" value="1" ng-model="option.isValid"/>
                            </td>
                            <td>
                               <button ng-click="deleteOption(question,option)" class="button button-red">x</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="align-right">
                <button type="button" ng-click="addQuestion()" class="button button-orange margin-bottom">ADD QUESTION</button>
            </div>
        </div>
        
        <div class="align-right">
            <button ng-click="saveTopic()" class="button button-green" >SAVE</button>
        </div>
        
</div>
