<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Chat</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body>
        <form name="joinChatForm" th:action="@{/login}" method="post">
            <fieldset>
                <legend>Chat</legend>
                <label for="user">User: </label>
                <input type="text" id="user" name="user"/>
                <div class="form-actions">
                    <button type="submit" class="btn">Join Chat</button>
                </div>
            </fieldset>
        </form>
    </body>
</html>