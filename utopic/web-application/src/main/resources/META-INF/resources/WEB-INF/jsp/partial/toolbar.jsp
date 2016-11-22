<nav class="navbar navbar-fixed-top navbar-dark">
    <div class="container">
        <div class="toggle" ng-click="toggleMenu()">
            <i class="fa fa-bars" aria-hidden="true"></i>
        </div>
        <a class="navbar-brand" href="/home"><img src="/images/logo.png"/></a>
        <div id="search-container">
            <form>
                <input type="text" placeholder="Search"/>
                <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
            </form>
        </div>
        <ul class="nav navbar-nav pull-md-right" ng-controller="indexController">
            <li ng-if="auths.indexOf('ROLE_CREATOR')!=-1"><a href="#create-topic-modal" class="nav-item nav-link  button button-orange">NEW TOPIC</a></li>
            <li><a href="/home" class="nav-item nav-link">HOME</a></li>
            <li><a href="/profile/${user.id}" class="nav-item nav-link button">PROFILE</a></li>
            <li><a href="/logout" class="nav-item nav-link button">LOG OUT</a></li>
        </ul>
    </div>
</nav>