$(document).ready(function () {
    $('button.myCategoryClass').click(function () {

        $('button.myCategoryClass').addClass('btn-outline-primary').removeClass('btn-primary');
        if ($(this).hasClass('btn-outline-primary')) {

            $(this)
                .removeClass('btn-outline-primary')
                .addClass('btn-primary');
        }
        let currentCategoryName = this.id;
        console.log(currentCategoryName);
    })
});
