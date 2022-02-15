<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\Balcao;

class BalcaoController extends Controller
{
  public function getAllBalcao()
  {
    $balcao = Balcao::get()->toJson(JSON_PRETTY_PRINT);
    return response($balcao, 200);
  }

  public function createBalcao(Request $request)
  {
    $balcao = new Balcao;
    $balcao->nomeCliente = $request->nomeCliente;
    $balcao->save();


    return response()->json([
      "message" => "mesa record created"
    ], 201);
  }

  public function getBalcao($id)
  {
    if (Balcao::where('id', $id)->exists()) {
      $balcao = Balcao::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
      return response($balcao, 200);
    } else {
      return response()->json([
        "message" => "mesa not found"
      ], 404);
    }
  }

  public function updateBalcao(Request $request, $id)
  {
    if (Balcao::where('id', $id)->exists()) {
      $balcao = Mesas::find($id);
      $balcao->nomeCliente = is_null($request->nomeCliente) ? $balcao->balcao : $request->nomeCliente;
      $balcao->save();

      return response()->json([
        "message" => "records updated successfully"
      ], 200);
    } else {
      return response()->json([
        "message" => "balcao not found"
      ], 404);
    }
  }

  public function deleteBalcao($id)
  {
    if (Balcao::where('id', $id)->exists()) {
      $balcao = Balcao::find($id);
      $balcao->delete();

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
        $balcao = Balcao::all();
        return view('balcao.list', compact('balcao','balcao'));
    }
 
   
    public function create()
    {
        //
        return view('balcao.create');
    }
 
   
    public function store(Request $request)
    {
        //
        $request->validate([
            'txtNomeCliente'=>'required',
        ]);
 
        $balcao = new Balcao([
            'nomeCliente' => $request->get('txtNomeCliente')
        ]);
 
        $balcao->save();
        return redirect('/balcao')->with('success', 'balcao has been added');
    }
 
   
    public function show(Balcao $balcao)
    {
        return view('balcao.view',compact('balcao'));
    }
 
    
    public function edit(Balcao $balcao)
    {
        //
        return view('balcao.edit',compact('balcao'));
    }
 
   
    public function update(Request $request,$id)
    {
        //
 
        $request->validate([
          'txtNomeCliente'=>'required',
        ]);
 
 
        $balcao = Balcao::find($id);
        $balcao->nomeCliente = $request->get('txtNomeCliente');
 
        $balcao->update();
 
        return redirect('/balcao')->with('success', 'balcao updated successfully');
    }
 
    
    public function destroy(Balcao $balcao)
    {
        //
        $balcao->delete();
        return redirect('/balcao')->with('success', 'balcao deleted successfully');
    }

}
