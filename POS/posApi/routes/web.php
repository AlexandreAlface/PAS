<?php

use App\Http\Controllers\ComidaController;
use App\Http\Controllers\MesasController;
use App\Http\Controllers\PedidoController;
use App\Http\Controllers\UtilizadoresController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');


Route::resource('utilizadores', UtilizadoresController::class);

Route::resource('mesas', MesasController::class);

Route::resource('balcao', BalcaoController::class);

Route::resource('comidas', ComidaController::class);

Route::resource('pedidos', PedidoController::class);

Route::resource('comidaPorPedidos', ComidasPorPedidoController::class);

