/**
 * Bootstrap validate
 * English lang module
 */

$.bt_validate.fn = {
  'required' : function(value) { return (value  != null) && (value != '')},
  'email' : function(value) { return /^[a-z0-9-_\.]+@[a-z0-9-_\.]+\.[a-z]{2,4}$/.test(value) },
  'www' : function(value) { return /^(http:\/\/)|(https:\/\/)[a-z0-9\/\.-_]+\.[a-z]{2,4}$/.test(value) },
  'date' : function(value) { return /^[\d]{2}\/[\d]{2}\/[\d]{4}$/.test(value) },
  'time' : function(value) { return /^[\d]{2}:[\d]{2}(:{0,1}[\d]{0,2})$/.test(value) },
  'datetime' : function(value) { return /^[\d]{2}\/[\d]{2}\/[\d]{4} [\d]{2}:[\d]{2}:{0,1}[\d]{0,2}$/.test(value) },
  'number' : function(value) { return /^[\d]+$/.test(value) },
  'float' : function(value) { return /^([\d]+)|(\d]+\.[\d]+)$/.test(value) },
  'equal' : function(value, eq_value) { return (value == eq_value); },
  'min' : function(value, min) { return Number(value) >= min },
  'max' : function(value, max) { return Number(value) <= max },
  'between' : function(value, min, max) { return (Number(value) >= min) && (Number(value) <= max)},
  'length_min' : function(value, min) { return value.length >= min },
  'length_max' : function(value, max) { return value.length <= max },
  'length_between' : function(value, min, max) { return (value.length >= min) && (value.length <= max)}
}

$.bt_validate.text = {
  'required' : 'Это поле не может быть пустым',
  'email' : 'Не корректно введен email',
  'www' : 'Не корректно введен адрес',
  'date' : 'Не корректно введена дата',
  'time' : 'Не корректно введено время',
  'datetime' : 'Не корректно введен datetime',
  'number' : 'Число должно быть целым',
  'float' : 'Не является числом с плавающей точкой',
  'equal' : 'Значение должно быть одинаковым с "%1"',
  'min' : 'Значение должно быть больше или равно %1',
  'max' : 'Значение должно быть меньше или равно %1',
  'between' : 'Значение должно быть между %1 и %2',
  'length_min' : 'Длинна значения должна быть больше или равна %1',
  'length_max' : 'Длинна значения должна быть меньше или равна %1',
  'length_between' : 'Длинна значения должна быть между %1 и %2'
}