/**
 * Created by hugo on 05/06/2017.
 */

$(function () {
    $(".ui.item.login").click(function () {
        $(".ui.login.modal").modal("show")
    })

    $('.ui.item.logout')
        .popup({
            popup : $('.ui.logout.popup'),
            on    : 'click'
        })
    ;

});