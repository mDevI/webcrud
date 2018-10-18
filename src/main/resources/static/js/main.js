$(document).ready(function () {

    $('.newBtn, .table .editBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        // if we have got a click on Edit button
        if (text === 'Edit') {
            $.get(href, function (person, status) {
                $('.modalEditForm #id').val(person.id);
                $('.modalEditForm #firstname').val(person.firstName);
                $('.modalEditForm #lastname').val(person.lastName);
                $('.modalEditForm #email').val(person.email);
            });

            console.debug('I am here!');
            $('.modalEditForm #personFormModal').modal();
            // it seems like a click on the New button
        } else {
            // init all fields
            $('.modalEditForm #id').val('');
            $('.modalEditForm #firstname').val('');
            $('.modalEditForm #lastname').val('');
            $('.modalEditForm #email').val('');
            // show the modal
            $('.modalEditForm #personFormModal').modal();
        }
    });

    $('.table .deleteBtn').on('click', function (e) {
        e.preventDefault();
        var href = $(this).attr('href');
        $('.modalDeleteDialog #delConfirm').attr('href', href);
        $('#personDeleteModal').modal();
    });

});