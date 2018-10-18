$(document).ready(function () {

    $('.newBtn, .table .editBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        // if we have got a click on Edit button
        if (text === 'Edit') {
            $.get(href, function (book, status) {
                $('.modalEditForm #id').val(book.id);
                $('.modalEditForm #title').val(book.title);
                $('.modalEditForm #genre').val(book.genre);
                $('.modalEditForm #author').val(book.author);
            });

            // console.debug('I am here!');
            $('.modalEditForm #bookFormModal').modal();
            // it seems like a click on the New button
        } else {
            // init all fields
            $('.modalEditForm #id').val('');
            $('.modalEditForm #title').val('');
            $('.modalEditForm #genre').val('');
            $('.modalEditForm #author').val('');
            // show the modal
            $('.modalEditForm #bookFormModal').modal();
        }
    });

    $('.table .deleteBtn').on('click', function (e) {
        e.preventDefault();
        var href = $(this).attr('href');
        $('.modalDeleteDialog #delConfirm').attr('href', href);
        $('#bookDeleteModal').modal();
    });

});