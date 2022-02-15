<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\Comida;

class ComidaController extends Controller
{
  public function getAllComida()
  {

    $comida = Comida::get()->toJson(JSON_PRETTY_PRINT);
    return response($comida, 200);

  }

  public function createComida(Request $request)
  {
    
    $comida = new Comida;
    $comida->nome = $request->nome;
    $comida->valor = $request->valor;
    $comida->save();

    return response()->json([
      "message" => "mesa record created"
    ], 201);

  }

  public function getComida($id)
  {
    if (Comida::where('id', $id)->exists()) {
      $comida = Comida::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
      return response($comida, 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function updateComida(Request $request, $id)
  {
    if (Comida::where('id', $id)->exists()) {
      $comida = Comida::find($id);
      $comida->nome = is_null($request->nome) ? $comida->nome : $request->nome;
      $comida->valor = is_null($request->valor) ? $comida->valor : $request->valor;
      $comida->save();

      return response()->json([
        "message" => "records updated successfully"
      ], 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function deleteComida($id)
  {
    if (Comida::where('id', $id)->exists()) {
      $comida = Comida::find($id);
      $comida->delete();

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
      $comidas = Comida::all();
      return view('comidas.list', compact('comidas','comidas'));
  }

 
  public function create()
  {
      //
      return view('comidas.create');
  }

 
  public function store(Request $request)
  {
      //
      $request->validate([
          'txtNome'=>'required',
          'txtValor'=>'required'
      ]);

      $comidas = new Comida([
          'nome' => $request->get('txtNomeCliente'),
          'valor' => $request->get('txtValor')
      ]);

      $comidas->save();
      return redirect('/comidas')->with('success', 'comidas has been added');
  }

 
  public function show(Comida $comidas)
  {
      return view('comidas.view',compact('comidas'));
  }

  
  public function edit(Comida $comidas)
  {
      //
      return view('comidas.edit',compact('comidas'));
  }

 
  public function update(Request $request,$id)
  {
      //

      $request->validate([
        'txtNome'=>'required',
        'txtValor'=>'required'
      ]);


      $comidas = Comida::find($id);
      $comidas->nome = $request->get('txtNome');
      $comidas->valor = $request->get('txtValor');

      $comidas->update();

      return redirect('/comidas')->with('success', 'comidas updated successfully');
  }

  
  public function destroy(Comida $comidas)
  {
      //
      $comidas->delete();
      return redirect('/comidas')->with('success', 'comidas deleted successfully');
  }

}
