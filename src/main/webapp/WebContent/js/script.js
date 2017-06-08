/**
 * Created by hugo on 05/06/2017.
 */

$(function () {

    $(".ui.login.modal")
        .modal('setting', 'closable', false).modal("show");
    $(".ui.item.login").click(function () {
        $(".ui.login.modal").modal("show")
    })

    $('.ui.item.logout')
        .popup({
            popup : $('.ui.logout.popup'),
            on    : 'click'
        })
    ;

    $('.modal .cards .image').dimmer({
        on: 'hover'
    });
    
    $('.addLikeMeter.modal')
        .modal('attach events', '.addService.modal .button.likemeter')
    ;
    $('.addGuestbook.modal')
        .modal('attach events', '.addService.modal .button.guestbook')
    ;
    $('.addNewsletter.modal')
        .modal('attach events', '.addService.modal .button.newsletter')
    ;
});