<?php

use App\Http\Controllers\BalcaoController;
use App\Http\Controllers\ComidaController;
use App\Http\Controllers\ComidasPorPedidoController;
use App\Http\Controllers\MesasController;
use App\Http\Controllers\PedidoController;
use App\Http\Controllers\UtilizadoresController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

//utilizadores

//

Route::get('/getAllUtilizadores', [UtilizadoresController::class,'getAllUtilizadores']);
Route::get('utilizadores/getUtilizador/{id}', [UtilizadoresController::class,'getUtilizador']);
Route::post('utilizadores/criarUtilizador', [UtilizadoresController::class,'createUtilizador']);
Route::get('utilizadores/login/{username}/{password}', [UtilizadoresController::class,'getLogin']);
Route::put('utilizadores/updateUtilizador/{id}', [UtilizadoresController::class,'updateUtilizador']);
Route::delete('utilizadores/deleteUtilizador/{id}',[UtilizadoresController::class,'deleteUtilizador']);

//mesas

Route::get('mesas/getAllMesas', [MesasController::class,'getAllMesas']);
Route::get('mesas/getMesa/{id}', [MesasController::class,'getMesa']);
Route::post('mesas/createMesa', [MesasController::class,'createMesa']);
Route::put('mesas/updateMesa/{id}', [MesasController::class,'updateMesa']);
Route::delete('mesas/deleteMesa/{id}',[MesasController::class,'deleteMesa']);

//balcao

Route::get('balcaos/getAllBalcao', [BalcaoController::class,'getAllBalcao']);
Route::get('balcaos/getBalcao/{id}', [BalcaoController::class,'getBalcao']);
Route::post('balcaos/createBalcao', [BalcaoController::class,'createBalcao']);
Route::put('balcaos/updateBalcao/{id}', [BalcaoController::class,'updateBalcao']);
Route::delete('balcaos/deleteBalcao/{id}',[BalcaoController::class,'deleteBalcao']);

//comida

Route::get('comida/getAllComida', [ComidaController::class,'getAllComida']);
Route::get('comida/getComida/{id}', [ComidaController::class,'getComida']);
Route::post('comida/createComida', [ComidaController::class,'createComida']);
Route::put('comida/updateComida/{id}', [ComidaController::class,'updateComida']);
Route::delete('comida/deleteComida/{id}}',[ComidaController::class,'deleteComida']);

//Pedido

Route::post('pedidos/postPedidoByMesa', [PedidoController::class,'postPedidoByMesa']);
Route::post('pedidos/postPedidoByBalcao', [PedidoController::class,'postPedidoByBalcao']);

Route::get('pedidos/getAllPedido', [PedidoController::class,'getAllPedido']);
Route::get('pedidos/getPedido/{id}', [PedidoController::class,'getPedido']);
Route::get('pedidos/getPedidobyMesa/{idMesa}', [PedidoController::class,'getPedidoByMesa']);
Route::get('pedidos/getPedidobyBalcao/{idBalcao}', [PedidoController::class,'getPedidoByBalcao']);
Route::post('pedidos/createPedido', [PedidoController::class,'createPedido']);
Route::put('pedidos/updatePedido/{id}', [PedidoController::class,'updatePedido']);
Route::delete('pedidos/deletePedido/{id}}',[PedidoController::class,'deletePedido']);

//ComidaporPedido

Route::get('ComidasPorPedido/getComidasPorPedidoByIdPedido/{idPedido}', [ComidasPorPedidoController::class,'getComidasPorPedidoByIdPedido']);

Route::get('ComidasPorPedido/getAllComidasPorPedido', [ComidasPorPedidoController::class,'getAllComidasPorPedido']);
Route::get('ComidasPorPedido/getComidasPorPedido/{id}', [ComidasPorPedidoController::class,'getComidasPorPedido']);
Route::post('ComidasPorPedido/createComidasPorPedido', [ComidasPorPedidoController::class,'createComidasPorPedido']);
Route::put('ComidasPorPedido/updateComidasPorPedido/{id}', [ComidasPorPedidoController::class,'updateComidasPorPedido']);
Route::delete('ComidasPorPedido/deleteComidasPorPedido/{id}',[ComidasPorPedidoController::class,'deleteComidasPorPedido']);
Route::delete('ComidasPorPedido/deleteComidasPorIdPedido/{idPedido}',[ComidasPorPedidoController::class,'deleteComidasPorIdPedido']);

