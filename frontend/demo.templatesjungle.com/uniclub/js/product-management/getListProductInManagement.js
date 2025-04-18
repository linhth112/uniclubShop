$(document).ready(function () {
  $(document).ready(function () {
    $("#table-product").DataTable({
      ajax: {
        method: "GET",
        url: "http://localhost:8080/product",
        dataSrc: function (json) {
          console.log(json);

          return json;
        },
      },
      columns: [
        { data: "idProduct" },
        { data: "name" },
        { data: "quantity" },
        { data: "price" },
        { data: "category" },
        {
          data: "description",
          render: function (data, type, row) {
            return `<span class="pdm-description">${data}</span>`;
          },
        },
        {
          render: function (data, type, row) {
            return `<button class="btn btn-dark btn-details" product-name="${row.name}" data-product-id="${row.idProduct}">Chi tiết</button>`;
          },
        },
        {
          render: function (data, type, row) {
            return `<button class="btn btn-dark" id="btn-edit-product" data-product-id="${row.idProduct}">Chỉnh sửa</button>
                      <button class="btn btn-dark" id="btn-delete-product" data-product-id="${row.idProduct}">Xóa</button>`;
          },
        },
      ],
    });
  });
});
