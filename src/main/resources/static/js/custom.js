// $('#browse_btn, #image_file1').on('click', function() {
//     $('#image_file').trigger("click");
// });
// $('#image_file').change(function() {
//     let file_name = this.value.replace(/\\/g, '/').replace(/.*\//, '');
//     $('#image_file1').val(file_name);
// });
//
//
// $(document).ready(function() {
//     $("#btn_submit_img").on('click', function() {
//         $("#upload_img_form").submit();
//     });
// });

'use strict';



(function($){

    $(function(){
        $('.truncate').succinct({
            size: 240
        });
    });


    /*------------------
        Preloader
    --------------------*/
    $(window).on('load', function () {
        $(".loader").fadeOut();
        $("#preloder").delay(200).fadeOut("slow");
        /*------------------
           Product filter
       --------------------*/
        if ($('#category-filter').length > 0) {
            var containerEl = document.querySelector('#category-filter');
            var mixer = mixitup(containerEl);
        }
        $(".categories-filter-section .filter-item ul li").on('click', function () {
            $(".categories-filter-section .filter-item ul li").removeClass('active');
            $(this).addClass('active');
        });
    });

    /*------------------
        Background Set
    --------------------*/
    $('.set-bg').each(function () {
        var bg = $(this).data('setbg');
        $(this).css('background-image', 'url(' + bg + ')');
    });


    // Search model
    $('.search-switch').on('click', function() {
        $('.search-model').fadeIn(400);
    });

    $('.search-close-switch').on('click', function() {
        $('.search-model').fadeOut(400,function(){
            $('#search-input').val('');
        });
    });


    /*------------------
		Navigation
	--------------------*/
    $(".mobile-menu").slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true
    });

    /*-------------------
		Category Select
	--------------------- */
    $('#category').niceSelect();

    /*-------------------
		Local Select
	--------------------- */
    $('#tag').niceSelect();

})(jQuery);