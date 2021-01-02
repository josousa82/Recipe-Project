$('#browse_btn, #image_file1').on('click', function() {
    $('#image_file').trigger("click");
});
$('#image_file').change(function() {
    let file_name = this.value.replace(/\\/g, '/').replace(/.*\//, '');
    $('#image_file1').val(file_name);
});