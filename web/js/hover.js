/* 
 * Written by Rish Shadra.
 * 
 */

//<![CDATA[ 
$(window).load(function(){
$("#credittext").attr('data-originalText', function () {
    return (this.textContent || this.innerText).trim();
}).hover(function () {
    $(this).fadeOut(600, function () {
        $(this).text('Designed by Rish Shadra. Hosted by Evan Goldstein.').fadeIn();
    });
},

function () {
    $(this).fadeOut(600, function () {
        $(this).text($(this).attr('data-originalText')).fadeIn();
    });
});
});//]]> 


