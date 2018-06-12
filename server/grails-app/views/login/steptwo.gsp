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
    (function() {
        document.forms['stepTwoLoginForm'].elements['token'].focus();
    })();
    // -->
</script>