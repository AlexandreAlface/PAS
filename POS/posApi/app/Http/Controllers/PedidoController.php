<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\Pedidos;

class PedidoController extends Controller
{

public function postPedidoByMesa(Request $request)
{

    $postPedido = Pedidos::where('idMesa', $request->idMesa)->first();
    $postPedido-> save();
    
    return response()->json([
      "message" => "mesa record created"
    ], 201);

}

public function postPedidoByBalcao(Request $request)
{

  $postPedido = Pedidos::where('idBalcao', $request->idBalcao)->first();
    $postPedido-> save();
    
    return response()->json([
      "message" => "mesa record created"
    ], 201);

}

  public function getAllPedido()
  {
    $pedido = Pedidos::get()->toJson(JSON_PRETTY_PRINT);
    return response($pedido, 200);
  }

  public function createPedido(Request $request)
  {
    $pedido = new Pedidos;
    $pedido->idMesa = $request->idMesa;
    $pedido->idBalcao = $request->idBalcao;
    $pedido->save();

    return response()->json([
      "message" => "mesa record created"
    ], 201);
  }

  public function getPedidoByMesa($idMesa)
  {
    if (Pedidos::where('idMesa', $idMesa)->exists()) {
      $pedido = Pedidos::where('idMesa', $idMesa)->get()->toJson(JSON_PRETTY_PRINT);
      return response($pedido, 200);
    } else {
      return response()->json([
        "message" => "pedido not found"
      ], 404);
    }
  }

  public function getPedidoByBalcao($idBalcao)
  {
    if (Pedidos::where('idBalcao', $idBalcao)->exists()) {
      $pedido = Pedidos::where('idBalcao', $idBalcao)->get()->toJson(JSON_PRETTY_PRINT);
      return response($pedido, 200);
    } else {
      return response()->json([
        "message" => "pedido not found"
      ], 404);
    }
  }

  public function getPedido($id)
  {
    if (Pedidos::where('id', $id)->exists()) {
      $pedido = Pedidos::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
      return response($pedido, 200);
    } else {
      return response()->json([
        "message" => "pedido not found"
      ], 404);
    }
  }
  

  public function updatePedido(Request $request, $id)
  {
    if (Pedidos::where('id', $id)->exists()) {
      $pedido = Pedidos::find($id);
      $pedido->idMesa = is_null($request->idMesa) ? $pedido->idMesa : $request->idMesa;
      $pedido->idBalcao = is_null($request->idBalcao) ? $pedido->idBalcao : $request->idBalcao;
      $pedido->save();

      return response()->json([
        "message" => "records updated successfully"
      ], 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function deletePedido($id)
  {
    if (Pedidos::where('id', $id)->exists()) {
      $pedido = Pedidos::find($id);
      $pedido->delete();

      return response()->json([
        "message" => "records deleted"
      ], 202);
    } else {
      return response()->json([
        "message" => "mesas not found"
      ], 404);
    }
  }

  public function index()
  {
      //
      $pedidos = Pedidos::all();
      return view('pedidos.list', compact('pedidos','pedidos'));
  }

 
  public function create()
  {
      //
      return view('pedidos.create');
  }

 
  public function store(Request $request)
  {
      //
      $request->validate([
        'txtIdMesa'=>'required',
        'txtIdBalcao'=>'required'
      ]);

      $pedidos = new Pedidos([
          'idMesa' => $request->get('txtIdMesa'),
          'idBalcao' => $request->get('txtIdBalcao')
      ]);

      $pedidos->save();
      return redirect('/pedidos')->with('success', 'pedidos has been added');
  }

 
  public function show(Pedidos $pedidos)
  {
      return view('pedidos.view',compact('pedidos'));
  }

  
  public function edit(Pedidos $pedidos)
  {
      //
      return view('pedidos.edit',compact('pedidos'));
  }

 
  public function update(Request $request,$id)
  {
      //

      $request->validate([
        'txtIdMesa'=>'required',
        'txtIdBalcao'=>'required'
      ]);


      $pedidos = Pedidos::find($id);
      $pedidos->idMesa = $request->get('txtIdMesa');
      $pedidos->idBalcao = $request->get('txtIdMesa');

      $pedidos->update();

      return redirect('/pedidos')->with('success', 'pedidos updated successfully');
  }

  
  public function destroy(Pedidos $pedidos)
  {
      //
      $pedidos->delete();
      return redirect('/pedidos')->with('success', 'pedidos deleted successfully');
  }

}
