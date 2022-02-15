<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\Utilizadores;

class UtilizadoresController extends Controller
{

  public function getLogin(Request $request, $username, $password)
  {
    if (Utilizadores::where('username', $username)->where('password', $password)->exists()) {
      $login = Utilizadores::where('username', $username)->where('password', $password)->get()->toJson(JSON_PRETTY_PRINT);
      return response($login, 200);
    } else {
      return response()->json(["message" => "Utilizador nao encontrado por email e pass"], 404);
    }
  }

  public function getAllUtilizadores()
  {

    $utilizadores = Utilizadores::get()->toJson(JSON_PRETTY_PRINT);
    return response($utilizadores, 200);
  }

  public function createUtilizador(Request $request)
  {

    $utilizadores = new Utilizadores;
    $utilizadores->username = $request->username;
    $utilizadores->password = $request->password;
    $utilizadores->name = $request->name;
    $utilizadores->save();


    return response()->json([
      "message" => "user record created"
    ], 201);
  }


  public function getUtilizador($id)
  {
    if (Utilizadores::where('id', $id)->exists()) {
      $utilizadores = Utilizadores::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
      return response($utilizadores, 200);
    } else {
      return response()->json([
        "message" => "Student not found"
      ], 404);
    }
  }

  public function updateUtilizador(Request $request, $id)
  {
    if (Utilizadores::where('id', $id)->exists()) {
      $utilizadores = Utilizadores::find($id);
      $utilizadores->username = is_null($request->username) ? $utilizadores->username : $request->username;
      $utilizadores->password = is_null($request->password) ? $utilizadores->password : $request->password;
      $utilizadores->name = is_null($request->name) ? $utilizadores->name : $request->name;
      $utilizadores->save();

      return response()->json([
        "message" => "records updated successfully"
      ], 200);
    } else {
      return response()->json([
        "message" => "Utilizador not found"
      ], 404);
    }
  }


  public function deleteUtilizador($id)
  {
    if (Utilizadores::where('id', $id)->exists()) {
      $utilizadores = Utilizadores::find($id);
      $utilizadores->delete();

      return response()->json([
        "message" => "records deleted"
      ], 202);
    } else {
      return response()->json([
        "message" => "Utilizadores not found"
      ], 404);
    }
  }

  
//////////////////////////////


    public function index()
    {
        //
        $utilizadores = Utilizadores::all();
        return view('utilizadores.list', compact('utilizadores','utilizadores'));
    }
 
   
    public function create()
    {
        //
        return view('utilizadores.create');
    }
 
   
    public function store(Request $request)
    {
        //
        $request->validate([
            'txtUsername'=>'required',
            'txtPassword'=> 'required',
            'txtName' => 'required'
        ]);
 
        $utilizador = new Utilizadores([
            'username' => $request->get('txtUsername'),
            'password'=> $request->get('txtPassword'),
            'name'=> $request->get('txtName'),
        ]);
 
        $utilizador->save();
        return redirect('/utilizadores')->with('success', 'utilizador has been added');
    }
 
   
    public function show(Utilizadores $utilizadores)
    {
        return view('utilizadores.view',compact('utilizadores'));
    }
 
    
    public function edit(Utilizadores $utilizadores)
    {
        //
        return view('utilizadores.edit',compact('utilizadores'));
    }
 
   
    public function update(Request $request,$id)
    {
        //
 
        $request->validate([
          'txtUsername'=>'required',
          'txtPassword'=> 'required',
          'txtName' => 'required'
        ]);
 
 
        $utilizadores = Utilizadores::find($id);
        $utilizadores->username = $request->get('txtUsername');
        $utilizadores->password = $request->get('txtPassword');
        $utilizadores->name = $request->get('txtName');
 
        $utilizador->update();
 
        return redirect('/utilizadores')->with('success', 'utilizador updated successfully');
    }
 
    
    public function destroy(Utilizadores $utilizadores)
    {
        //
        $utilizadores->delete();
        return redirect('/utilizador')->with('success', 'Utilizador deleted successfully');
    }


}
