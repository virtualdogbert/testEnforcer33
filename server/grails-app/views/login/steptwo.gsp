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

    <div id='login'>
        <div class='inner'>
            <div class='fheader'><g:message code="securitytoken.header"/></div>

            <g:if test='${flash.message}'>
                <div class='login_message'>${flash.message}</div>
            </g:if>

            <form action='${postUrl}' method='POST' id='stepTwoLoginForm' class='cssform' autocomplete='off'>
                <p>
                    <label for='token'><g:message code="securitytoken.label"/>:</label>
                    <input type='text' class='text_' name='token' id='token'/>
                </p>

                <p>
                    <input type='submit' id="submit" value='${message(code: "securitytoken.button")}'/>
                </p>
            </form>
        </div>
    </div>
    <script type='text/javascript'>
        <!--
        (function () {
            document.forms['stepTwoLoginForm'].elements['token'].focus();
        })();
        // -->
    </script>
</div>
</body>
</html>