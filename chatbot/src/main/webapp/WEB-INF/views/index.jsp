<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>hasCode.com - Java EE 7 Websocket Tutorial</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Micha Kops">

        <script src="resources/js/jquery-1.10.2.min.js"></script>
        <script src="resources/js/custom.js"></script>

        <!-- Le styles -->
        <link href="./resources/css/bootstrap.css" rel="stylesheet">
        <link href="./resources/css/custom.css" rel="stylesheet">

        <link href="./resources/css/bootstrap-responsive.css" rel="stylesheet">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
        <script src="./resources/sjs/html5shiv.js"></script>
        <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="./resources/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="./resources/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="./resources/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="./resources/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="./resources/ico/favicon.png">

    </head>

    <body>

        <div class="container chat-signin">
            <form class="form-signin">
                <h2 class="form-signin-heading">Chat sign in</h2>
                <label for="nickname">Nickname</label> <input type="text"
                                                              class="input-block-level" placeholder="Nickname"
                                                              id="nickname">
                <div class="btn-group">
                    <label for="chatroom">Chatroom</label> <select size="1"
                                                                   id="chatroom">
                    <option>Gomel</option>
                </select>
                </div>
                <button class="btn btn-large btn-primary" type="submit"
                        id="enterRoom">Sign in
                </button>
            </form>
        </div>
        <!-- /container -->

        <div class="container chat-wrapper">
            <form id="do-chat">
                <h2 class="alert alert-success"></h2>
                <table id="response" class="table table-bordered"></table>
                <fieldset>
                    <legend>Enter your message...</legend>
                    <div class="controls">
                        <input type="text" class="input-block-level" placeholder="Your message..." id="message"
                               style="height:60px"/>
                        <input type="submit" class="btn btn-large btn-block btn-primary"
                               value="Send message"/>
                        <button class="btn btn-large btn-block" type="button" id="leave-room">Leave
                            chat
                        </button>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>