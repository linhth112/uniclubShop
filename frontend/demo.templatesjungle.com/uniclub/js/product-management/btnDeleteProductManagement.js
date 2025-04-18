$(document).ready(function() {
    $("#table-product").on("click", "#btn-delete-product", function() {
        let productId = $(this).data("product-id");

        let isConfirmed = confirm("Bạn chắc chắn muốn xóa?");

        if (isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "http://localhost:8080/product",
                headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
                },
                data: { id: productId},
            }).done(function (result) {
                if (result) {
                result.data ? alert("Delete thành công") : alert("Delete thất bại");
                location.reload();
                }
            });
        }
    })
})