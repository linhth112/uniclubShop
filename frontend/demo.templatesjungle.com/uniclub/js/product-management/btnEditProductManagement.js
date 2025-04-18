$(document).ready(function () {
    $("#table-product").on("click", "#btn-edit-product", function () {
        let productId = $(this).data("product-id");
        let $row = $(this).closest("tr");

        let oldData = {
            name: $row.find('td:eq(1)').text(),
            price: $row.find('td:eq(3)').text(),
            category: $row.find('td:eq(4)').text(),
            description: $row.find('td:eq(5)').text()
        };

        $row.data("oldData", oldData);

        $row.find('td:eq(1), td:eq(3), td:eq(4), td:eq(5)')
            .attr('contenteditable', 'true')
            .wrapInner('<span style="color: red;"></span>')
            .addClass("border border-dark");

        $(this).text("Lưu").attr("id", "btn-save-product");
        $row.find("#btn-delete-product").text("Hủy").attr("id", "btn-cancel");
    });

    $("#table-product").on("click", "#btn-save-product", function () {
        let $row = $(this).closest("tr");
        let productId = $(this).data("product-id");

        let htmlProductName = $row.find('td:eq(1)').text();
        let htmlProductPrice = parseFloat($row.find('td:eq(3)').text());
        let htmlProductCategory = $row.find('td:eq(4)').text();
        let htmlProductDesciption = $row.find('td:eq(5)').text();

        let isConfirmed = confirm("Bạn chắc chắn muốn lưu thay đổi?");
        if (isConfirmed) {
            $.ajax({
                method: "PATCH",
                url: "http://localhost:8080/product",
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token"),
                    "Content-Type": "application/json",
                },
                data: JSON.stringify({
                    idProduct: productId,
                    name: htmlProductName,
                    price: htmlProductPrice,
                    description: htmlProductDesciption,
                    category: htmlProductCategory
                }),
            }).done(function (result) {
                alert(result.data ? "Update thành công" : "Update thất bại");
                location.reload();
            });
        }
    });

    $("#table-product").on("click", "#btn-cancel", function () {
        let $row = $(this).closest("tr");
        let oldData = $row.data("oldData");  

        let isConfirmed = confirm("Bạn chắc chắn muốn hủy?");
        if (isConfirmed && oldData) {
            $row.find('td:eq(1)').text(oldData.name);
            $row.find('td:eq(3)').text(oldData.price);
            $row.find('td:eq(4)').text(oldData.category);
            $row.find('td:eq(5)').text(oldData.description);

            $row.find('td:eq(1), td:eq(3), td:eq(4), td:eq(5)')
                .removeAttr('contenteditable')
                .removeClass("border border-dark")
                .find("span").contents().unwrap();

            $(this).text("Xóa").attr("id", "btn-delete-product");
            $row.find("#btn-save-product").text("Chỉnh sửa").attr("id", "btn-edit-product");
        }
    });
});
