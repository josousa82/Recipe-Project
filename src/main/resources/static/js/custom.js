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

(function($){

    $(function(){
        $('.truncate').succinct({
            size: 240
        });
    });

})(jQuery);