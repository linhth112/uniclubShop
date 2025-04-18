$(document).ready(function() {
    $("#btn-add-product").click(function() {
        if ($("#tbody tr[add-tr='1']").length > 0) {
            alert("Vui lòng lưu hoặc hủy dòng hiện tại trước khi thêm dòng mới.");
            return;
          }

        let rowCount = $("#tbody tr").length;
        let dataId = rowCount + 1;

        let html = `<tr add-tr="1">
                  <td ></td>
                  <td id="name-product" contenteditable="true"></td>
                  <td ></td>
                  <td id="price-product" contenteditable="true"></td>
                  <td id="category-product" contenteditable="true"></td>
                  <td id="description-product" contenteditable="true"></td>
                  <td>
                    <button class="btn btn-dark btn-save-product" data-id="${dataId}">Lưu</button>
                  </td>
                  <td> </td>
                </tr>`;

        $("#tbody").append(html);

        $("#tbody td .btn-save-product").click(function() {
            let productName = $("#name-product").text();
            let productQuantity = parseInt($("#quantity-product").text());
            let productPrice = parseFloat($("#price-product").text());
            let productCategory = $("#category-product").text();
            let productDescription = $("#description-product").text();

            console.log(productDescription + productName + productPrice + productQuantity);
            let isConfirmed = confirm("Bạn chắc chắn muốn lưu?");

            if (isConfirmed) {
                $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/product",
                    headers: {
                    Authorization: "Bearer " + localStorage.getItem("token"),
                    "Content-Type": "application/json",
                    },
                    data: JSON.stringify({ 
                            idProduct: "",
                            name: productName,
                            price: productPrice,
                            description: productDescription,
                            category: productCategory
                        }),
                }).done(function (result) {
                    if (result) {
                    result.data ? alert("Thêm sản phẩm thành công") : alert("Thêm sản phẩm thất bại");
                    location.reload();
                    }
                });
            }
            
        })
    })
})