export function isEmptyObject(object) {
  let isObjectEmpty=true;
  for (let key in object) {
    if (object[key] !== "") {
      isObjectEmpty=false;
      break;
    }
  }
  return isObjectEmpty;
}
