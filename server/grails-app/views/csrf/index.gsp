<!DOCTYPE html>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h1 class="strong-primary">Sign in form without csrf protection.</h1>
        </div>
    </div>


    <div class="row">

        <div class="col-md-4 text-center">
            <g:form controller="csrf" action="without">
                <div class="form-group">
                    <input type="email" class="form-control" id="signin-email1" placeholder="Your email">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="signin-password1" placeholder="Password">
                </div>
                <button type="submit" class="btn form-control btn-lg btn-success">Sign in</button>

                <div class="form-group" style="text-align: left;"><a href="#">Need help?</a></div>
            </g:form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h1 class="strong-primary">Sign in form without csrf token.</h1>
        </div>
    </div>

    <div class="row">

        <div class="col-md-4 text-center">
            <g:form controller="csrf" action="with">
                <div class="form-group">
                    <input type="email" class="form-control" id="signin-email2" placeholder="Your email">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="signin-password2" placeholder="Password">
                </div>
                <button type="submit" class="btn form-control btn-lg btn-success">Sign in</button>

                <div class="form-group" style="text-align: left;"><a href="#">Need help?</a></div>
            </g:form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h1 class="strong-primary">Sign in form with csrf token.</h1>
        </div>
    </div>

    <div class="row">

        <div class="col-md-4 text-center">
            <g:form controller="csrf" action="with" useToken="true">
                <div class="form-group">
                    <input type="email" class="form-control" id="signin-email3" placeholder="Your email">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="signin-password3" placeholder="Password">
                </div>
                <button type="submit" class="btn form-control btn-lg btn-success">Sign in</button>

                <div class="form-group" style="text-align: left;"><a href="#">Need help?</a></div>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>