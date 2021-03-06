@extends('layouts.app')

@section('content')
@if (Route::has('login'))
@auth
    <div class="card-header">{{ __('comidasPorPedido') }}</div>
    <div class="col-lg-1">
    </div>
    <div class="card-body">
        @if (session('status'))
            <div class="alert alert-success" role="alert">
                {{ session('status') }}
            </div>
        @endif
        <div class="col-lg-1">
            <a class="btn btn-success" href="{{ route('comidasPorPedido.create') }}">Add</a>
        </div>
        <table class="table table-bordered">
            <tr>
                <th>idPedido</th>
                <th>idComida</th>
                <th>Quantidade</th>
            </tr>
            @php
                $i = 0;
            @endphp
            @foreach ($comidasPorPedido as $comidasPorPedido)
                <tr>
                    <td>{{ ++$i }}</td>
                    <td>{{ $comidasPorPedido->idPedido}}</td>
                    <td>{{ $comidasPorPedido->idComida}}</td>
                    <td>{{ $comidasPorPedido->Quantidade}}</td>
                    <td>
                        <form action="{{ route('comidasPorPedido.destroy',$comidasPorPedido->id) }}" method="POST">
                            <a class="btn btn-info" href="{{ route('comidasPorPedido.show',$comidasPorPedido->id) }}">Show</a>
                            <a class="btn btn-primary" href="{{ route('comidasPorPedido.edit',$comidasPorPedido->id) }}">Edit</a>
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </table>

        

        {{ __('You are logged in!') }}
    </div>

    <div class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
        <a href="{{ url('/home') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Home</a>
    @else
        <a href="{{ route('login') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Log in</a>

        @if (Route::has('register'))
            <a href="{{ route('register') }}" class="ml-4 text-sm text-gray-700 dark:text-gray-500 underline">Register</a>
        @endif
    @endauth
</div>
    @endif
@endsection
