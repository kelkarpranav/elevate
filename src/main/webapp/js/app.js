angular.module("sprang.services", ["ngResource"]).
    factory('Book', function ($resource) {
        var Book = $resource('/api/books/:bookId', {bookId: '@id'},
            {update: {method: 'PUT'}});
        Book.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return Book;
    });

angular.module("sprang.services", ["ngResource"]).
factory('Brand', function ($resource) {
    var Brand = $resource('/api/brands/:brandId', {brandId: '@id'},
        {update: {method: 'PUT'}});
    Brand.prototype.isNew = function(){
        return (typeof(this.id) === 'undefined');
    }
    return Brand;
});

angular.module("sprang", ["sprang.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/books', {templateUrl: '/views/books/list.html', controller: BookListController})
            .when('/books/:bookId', {templateUrl: '/views/books/detail.html', controller: BookDetailController});
    });

angular.module("sprang", ["sprang.services"]).
config(function ($routeProvider) {
    $routeProvider
        .when('/brands', {templateUrl: '/views/brands/list.html', controller: BrandListController})
        .when('/brands/:brandId', {templateUrl: '/views/brands/detail.html', controller: BrandDetailController});
});

function BookListController($scope, Book) {
    $scope.books = Book.query();

    $scope.deleteBook = function(book) {
        book.$delete(function() {
            $scope.books.splice($scope.books.indexOf(book),1);
            toastr.success("Deleted");
        });
    }
}


function BrandListController($scope, Brand) {
    $scope.brands = Brand.query();

    $scope.deleteBrand = function(brand) {
    	brand.$delete(function() {
            $scope.brands.splice($scope.brands.indexOf(brand),1);
            toastr.success("Deleted");
        });
    }
}

function BookDetailController($scope, $routeParams, $location, Book) {
    var bookId = $routeParams.bookId;

    if (bookId === 'new') {
        $scope.book = new Book();
    } else {
        $scope.book = Book.get({bookId: bookId});
    }

    $scope.save = function () {
        if ($scope.book.isNew()) {
            $scope.book.$save(function (book, headers) {
                toastr.success("Created");
                var location = headers('Location');
                var id = location.substring(location.lastIndexOf('/') + 1);
                $location.path('/books/' + id);
            });
        } else {
            $scope.book.$update(function() {
                toastr.success("Updated");
            });
        }
    };
}


function BrandDetailController($scope, $routeParams, $location, Brand) {
    var brandId = $routeParams.brandId;

    if (brandId === 'new') {
        $scope.brand = new Brand();
    } else {
        $scope.brand = Brand.get({brandId: brandId});
    }

    $scope.save = function () {
        if ($scope.brand.isNew()) {
            $scope.brand.$save(function (brand, headers) {
                toastr.success("Created");
                var location = headers('Location');
                var id = location.substring(location.lastIndexOf('/') + 1);
                $location.path('/brands/' + id);
            });
        } else {
            $scope.brand.$update(function() {
                toastr.success("Updated");
            });
        }
    };
}
