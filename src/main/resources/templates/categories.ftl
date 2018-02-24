<html>
    <head>
        <title>BudgetMaster</title>
        <meta charset="UTF-8"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.css">
        <link type="text/css" rel="stylesheet" href="/css/main.css"/>
        <link type="text/css" rel="stylesheet" href="/css/style.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body class="budgetmaster-blue-light">
        <ul id="slide-out" class="side-nav fixed">
            <li><a href="" class="waves-effect" id="nav-logo-container"><img id="nav-logo" src="/images/Logo_with_text.png"></a></li>
            <li><div class="divider"></div></li>
            <li class="active"><a href="#!" class="waves-effect"><i class="material-icons">home</i>Startseite</a></li>
            <li><a href="#!" class="waves-effect"><i class="material-icons">list</i>Buchungen</a></li>
            <li>
                <ul class="collapsible collapsible-accordion no-padding">
                    <li>
                        <a class="collapsible-header nav-padding"><i class="material-icons">show_chart</i>Diagramme</a>
                        <div class="collapsible-body">
                            <ul>
                                <li><a href="#!" class="waves-effect"><span class="nav-margin">Eingaben/Ausgaben nach Kategorien</span></a></li>
                                <li><a href="#!" class="waves-effect"><span class="nav-margin">Eingaben/Ausgaben pro Monatn</span></a></li>
                                <li><a href="#!" class="waves-effect"><span class="nav-margin">Eingaben/Ausgaben nach Tagsn</span></a></li>
                                <li><a href="#!" class="waves-effect"><span class="nav-margin">Verbrauch nach Kategorienn</span></a></li>
                                <li><a href="#!" class="waves-effect"><span class="nav-margin">Histogrammn</span></a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </li>
            <li><a href="#!" class="waves-effect"><i class="material-icons">description</i>Berichte</a></li>
            <li><a href="#!" class="waves-effect"><i class="material-icons">label</i>Kategorien</a></li>
            <li><a href="#!" class="waves-effect"><i class="material-icons">settings</i>Einstellungen</a></li>
            <li><div class="divider"></div></li>
            <li><a href="#!" class="waves-effect"><i class="material-icons">lock</i>Logout</a></li>
        </ul>
        <a href="#" data-activates="slide-out" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>

        <main>
            <div class="card main-card">
                <div class="container">
                    <div class="section center-align">
                        <div class="grey-text text-darken-4 headline">Kategorien</div>
                    </div>
                </div>
                <div class="hide-on-small-only"><br></div>
                <div class="container">
                    <table class="bordered">
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>color</th>
                        </tr>
                        <#list model["categories"] as category>
                        <tr>
                            <td>${category.ID}</td>
                            <td>${category.name}</td>
                            <td>${category.color}</td>
                            <td>
                                <a href="/categories/${category.ID}/edit" class="btn-flat"><i class="material-icons left">edit</i></a>
                                <a href="/categories/${category.ID}/requestDelete" class="btn-flat"><i class="material-icons left">delete</i></a>
                            </td>
                        </tr>
                        </#list>
                    </table>
                </div>
            </div>

            <#if model["currentCategory"]??>
                <!-- confirm delete modal -->
                <div id="modalConfirmDelete" class="modal">
                    <div class="modal-content">
                        <h4>Kategorie löschen</h4>
                        <p>Möchtest du die Kategorie "${model["currentCategory"].name}" wirklich löschen?</p>
                    </div>
                    <div class="modal-footer">
                        <a href="#" class="modal-action modal-close waves-effect waves-red btn-flat ">Abbrechen</a>
                        <a href="/categories/${model["currentCategory"].ID}/delete" class="modal-action modal-close waves-effect waves-green btn-flat ">Löschen</a>
                    </div>
                </div>
            </#if>
        </main>

        <!--  Scripts-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
        <script src="/js/main.js"></script>
        <script src="/js/categories.js"></script>
    </body>
</html>