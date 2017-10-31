var page = {
    rows: 1,
    page: 10,
};


queryUser()

function queryUser() {
    $.ajax({
        url: `${commonUrl}/server/selServer`,
        type: 'POST',
        data: page,
        success: function (data) {
            console.log(data)
        },
        error: function (data) {
            console.log('in')
            // self.location='chat.html'; 
           
        }
    });
}