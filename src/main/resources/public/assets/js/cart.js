// add card
if($('.btn_add_cart').length > 0) {
    $('.btn_add_cart').on('click', function (e){
        let id     = this.getAttribute('data-id');
        let parent = this.parentElement;
        let input  = parent.querySelector('input');
        if (input != null){
            let value = input.value>>0;
            let max   = input.getAttribute('max')>>0;
            if (value < 1 || value > max){
                input.value = value = 1;
            }
        }else{

        }
    });
}

// cart manager
