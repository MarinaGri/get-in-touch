<#ftl encoding="UTF-8"/>

<#import '/spring.ftl' as spring/>
<#import 'util.ftl' as util/>

<#macro main title css listJs=[]>
    <html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8">
        <@util.static css listJs/>
        <title><@spring.message title/></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="header">
            <h2 class="app-name">GET IN TOUCH</h2>
            <div class="icon-group">
                <div class="dropdown">
                    <div class="dropbtn">LANG</div>
                    <div class="dropdown-content">
                        <a href="<@spring.url '/lang/?locale=ru_RU'/>">RU</a>
                        <a href="<@spring.url '/lang/?locale=en_US'/>">EN</a>
                    </div>
                </div>
                <a href="<@spring.url '/profile'/>">
                    <img class="icon" src="<@spring.url '/img/svg/person-fill.svg'/>" alt="profile">
                </a>
                <a href="<@spring.url '/posts/search/subscribers'/>">
                    <img class="icon" src="<@spring.url '/img/svg/list-stars.svg'/>" alt="profile">
                </a>
                <a href="<@spring.url '/search'/>">
                    <img class="icon" src="<@spring.url '/img/svg/card-heading.svg'/>" alt="feed">
                </a>
            </div>
        </div>
        <div class="main-row">
            <#nested>
        </div>
        <div class="footer">
            <@spring.message 'page.footer'/>
            <div class="icon-group">
                <a href="https://github.com/MarinaGri">
                    <img class="icon" src="<@spring.url '/img/svg/github.svg'/>" alt="github">
                </a>
                <a href="https://t.me/idpriv">
                    <img class="icon" src="<@spring.url '/img/svg/telegram.svg'/>" alt="telegram">
                </a>
            </div>
        </div>
    </div>
    </body>
    </html>
</#macro>
