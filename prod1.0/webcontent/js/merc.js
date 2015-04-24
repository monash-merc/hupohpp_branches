/**remove the message pane*/
$(document).ready(function () {
    $(".message_pane .delete").click(function () {
        $(".success_act_msg").remove();
    });
});


function loadRssFeeds (feedDiv) {
    if (feedDiv != null) {
        var numberItems = 5;
        $.ajax({
            type:'GET',
            url:'/tpb/rss/rss',
            dataType:'xml',
            error:function (xhr) {
                var html = rssFeedFailure(xhr);
                feedDiv.html(html);
            },
            success:function (xml) {
                var html = parseRSS(xml, numberItems);
                feedDiv.attr('class', "rssFeed");
                feedDiv.html(html);
            }
        });
    }
}

function rssFeedFailure(xhr) {
    var html = '';
    html += "<div class='rssHeader'>";
    html += "TPB Traffic Light RSS Feeds <img class='rssImg' src='images/rss.png' />";
    html += "</div>";
    html += "<div class='rssBody'>";
    html += "<div class='rssError'> Unable to load the RSS Feed.</div>";
    html += "</div>";
    html += "<div style='clear: both;'/>";
    return html;
}

function parseRSS(rss, num) {
    var html = '';
    var cTitle = $('title', rss).eq(0).text();
    var cLink = $('link', rss).eq(0).text();
    var cDesc = $('description', rss).eq(0).text();

    html += "<div class='rssHeader'>";
    html += "<a href='" + cLink + "' target='_blank'>" + cTitle + " <img class='rssImg' src='images/rss.png' /></a>";
    html += "</div>";

    html += '<div class="rssBody"><ul>';
    var i = 0;
    $('item', rss).each(function (index) {
        if (index < num) {
            var iTitle = $(this).find('title').eq(0).text();
            var iLink = $(this).find('link').eq(0).text();
            var iDesc = $(this).find('description').eq(0).text();
            var iPubDate = $(this).find('pubDate').eq(0).text();
            html += "<li class='rssRow'>";
            html += "<h4>";
            html += "<a href='" + iLink + "' target='_self'>" + iTitle + "</a>";
            html += "</h4>";
            html += "<p>" + iDesc + "</p>";
            html += "<div>" + iPubDate + "</div>";
            html += "</li>";
        }
    });

    html += "</u></div>";
    html += "<div style='clear: both;'/>";
    return html;
}
