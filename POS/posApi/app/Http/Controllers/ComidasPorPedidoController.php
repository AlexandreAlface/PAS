<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\ComidasPorPedido;

class ComidasPorPedidoController extends Controller
{

  public function getComidasPorPedidoByIdPedido($idPedido)
  {
    if (ComidasPorPedido::where('idPedido', $idPedido)->exists()) {
      $comidasPorPedido = ComidasPorPedido::where('idPedido', $idPedido)->get()->toJson(JSON_PRETTY_PRINT);
      return response($comidasPorPedido, 200);
    } else {
      return response()->json([
        "message" => "pedido not found"
      ], 404);
    }
  }


  public function getAllComidasPorPedido()
  {
    $comidasPorPedido = ComidasPorPedido::get()->toJson(JSON_PRETTY_PRINT);
    return response($comidasPorPedido, 200);
  }

  public function createComidasPorPedido(Request $request)
  {
    $comidasPorPedido = new ComidasPorPedido;
    $comidasPorPedido->idPedido = $request->idPedido;
    $comidasPorPedido->idComida = $request->idComida;
    $comidasPorPedido->Quantidade = $request->Quantidade;
    $comidasPorPedido->save();

    return response()->json([
      "message" => "mesa record created"
    ], 201);
  }

  public function getComidasPorPedido($id)
  {
    if (ComidasPorPedido::where('id', $id)->exists()) {
      $comidasPorPedido = ComidasPorPedido::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
      return response($comidasPorPedido, 200);
    } else {
      return response()->json([
        "message" => "pedido not found"
      ], 404);
    }
  }

  public function updateComidasPorPedido(Request $request, $id)
  {
    if (ComidasPorPedido::where('id', $id)->exists()) {
      $comidasPorPedido = ComidasPorPedido::find($id);
      $comidasPorPedido->idPedido = is_null($request->idPedido) ? $comidasPorPedido->idPedido : $request->idPedido;
      $comidasPorPedido->idComida = is_null($request->idComida) ? $comidasPorPedido->idComida : $request->idComida;
      $comidasPorPedido->Quantidade = is_null($request->Quantidade) ? $comidasPorPedido->Quantidade : $request->Quantidade;
      $comidasPorPedido->save();

      return response()->json([
        "message" => "records updated successfully"
      ], 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function deleteComidasPorIdPedido($idPedido)
  {
    $comidasPorPedido = ComidasPorPedido::where('idPedido',$idPedido)->first();

    if ($comidasPorPedido != null) {
      $comidasPorPedido->delete();
  
  }

  }

  public function deleteComidasPorPedido($id)
  {
    if (ComidasPorPedido::where('id', $id)->exists()) {
      $comidasPorPedido = ComidasPorPedido::find($id);
      $comidasPorPedido->delete();

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
      $comidasPorPedido = ComidasPorPedido::all();
      return view('comidasPorPedido.list', compact('comidasPorPedido','comidasPorPedido'));
  }

 
  public function create()
  {
      //
      return view('comidasPorPedido.create');
  }

 
  public function store(Request $request)
  {
      //
      $request->validate([
        'txtIdPedido'=>'required',
        'txtIdComida'=>'required',
        'txtQuantidade'=>'required'
      ]);

      $comidasPorPedido = new Pedidos([
          'idPedido' => $request->get('txtIdPedido'),
          'idComida' => $request->get('txtIdComida'),
          'Quantidade' => $request->get('txtQuantidade')
      ]);

      $comidasPorPedido->save();
      return redirect('/comidasPorPedido')->with('success', 'comidasPorPedido has been added');
  }

 
  public function show(ComidasPorPedido $comidasPorPedido)
  {
      return view('comidasPorPedido.view',compact('comidasPorPedido'));
  }

  
  public function edit(ComidasPorPedido $comidasPorPedido)
  {
      //
      return view('comidasPorPedido.edit',compact('comidasPorPedido'));
  }

 
  public function update(Request $request,$id)
  {
      //

      $request->validate([
        'txtIdPedido'=>'required',
        'txtIdComida'=>'required',
        'txtQuantidade'=>'required'
      ]);


      $comidasPorPedido = ComidasPorPedido::find($id);
      $comidasPorPedido->idPedido = $request->get('txtIdPedido');
      $comidasPorPedido->idComida = $request->get('txtIdComida');
      $comidasPorPedido->Quantidade = $request->get('txtQuantidade');

      $comidasPorPedido->update();

      return redirect('/comidasPorPedido')->with('success', 'comidasPorPedido updated successfully');
  }

  
  public function destroy(ComidasPorPedido $comidasPorPedido)
  {
      //
      $comidasPorPedido->delete();
      return redirect('/comidasPorPedido')->with('success', 'comidasPorPedido deleted successfully');
  }

}
