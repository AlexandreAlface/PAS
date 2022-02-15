<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\Mesas;

class MesasController extends Controller
{
  public function getAllMesas()
  {
    $mesas = Mesas::get()->toJson(JSON_PRETTY_PRINT);
    return response($mesas, 200);
  }

  public function createMesa(Request $request)
  {

    $mesas = new Mesas;
    $mesas->nomeCliente = $request->nomeCliente;
    $mesas->save();


    return response()->json([
      "message" => "mesa record created"
    ], 201);

  }

  public function getMesa($id)
  {
    if (Mesas::where('id', $id)->exists()) {
      $mesas = Mesas::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
      return response($mesas, 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function updateMesa(Request $request, $id)
  {
    if (Mesas::where('id', $id)->exists()) {
      $mesas = Mesas::find($id);
      $mesas->nomeCliente = is_null($request->nomeCliente) ? $mesas->mesas : $request->nomeCliente;
      $mesas->save();

      return response()->json([
        "message" => "records updated successfully"
      ], 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function deleteMesa($id)
  {
    if (Mesas::where('id', $id)->exists()) {
      $mesas = Mesas::find($id);
      $mesas->delete();

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
        $mesas = Mesas::all();
        return view('mesas.list', compact('mesas','mesas'));
    }
 
   
    public function create()
    {
        //
        return view('mesas.create');
    }
 
   
    public function store(Request $request)
    {
        //
        $request->validate([
            'txtNomeCliente'=>'required',
        ]);
 
        $mesas = new Mesas([
            'nomeCliente' => $request->get('txtNomeCliente')
        ]);
 
        $mesas->save();
        return redirect('/mesas')->with('success', 'mesas has been added');
    }
 
   
    public function show(Mesas $mesas)
    {
        return view('mesas.view',compact('mesas'));
    }
 
    
    public function edit(Mesas $mesas)
    {
        //
        return view('mesas.edit',compact('mesas'));
    }
 
   
    public function update(Request $request,$id)
    {
        //
 
        $request->validate([
          'txtNomeCliente'=>'required',
        ]);
 
 
        $mesas = Mesas::find($id);
        $mesas->nomeCliente = $request->get('txtNomeCliente');
 
        $mesas->update();
 
        return redirect('/mesas')->with('success', 'mesas updated successfully');
    }
 
    
    public function destroy(Mesas $mesas)
    {
        //
        $mesas->delete();
        return redirect('/mesas')->with('success', 'mesa deleted successfully');
    }

}
