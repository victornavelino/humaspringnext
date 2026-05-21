const API_BASE_URL = "http://localhost:8080/api";

export interface Persona {
  id?: number;
  nombre: string;
  apellido: string;
  dni: string;
  cuil?: string;
  sexo?: string;
  fechaNacimiento?: string;
}

export interface Alumno extends Persona {
  // Alumno specific properties if any
}

export interface Docente extends Persona {
  // Docente specific properties if any
}

export interface Carrera {
  id?: number;
  descripcion: string;
  resolucion?: string;
}

export interface Cohorte {
  id?: number;
  descripcion: string;
  carrera?: Carrera;
  anio?: any;
}

export interface Cuenta {
  id?: number;
  descripcion: string;
  codigo?: string;
}

export interface TipoIngreso {
  id?: number;
  descripcion: string;
}

export interface Ingreso {
  id?: number;
  fechaPago: string;
  importe: number;
  numeroRecibo: number;
  anulado: boolean;
  cuenta?: Cuenta;
  tipoIngreso?: TipoIngreso;
  alumno?: Alumno;
  concepto?: string;
}

export interface InscripcionAlumnos {
  id?: number;
  alumno: Alumno;
  cohorte: Cohorte;
  fechaInscripcion: string;
  cohorteOriginal?: Cohorte;
}

async function request<T>(endpoint: string, options: RequestInit = {}): Promise<T> {
  const headers = {
    "Content-Type": "application/json",
    ...options.headers,
  };
  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    ...options,
    headers,
  });

  if (!response.ok) {
    const errorText = await response.text().catch(() => "");
    throw new Error(errorText || `HTTP error! status: ${response.status}`);
  }

  // Handle empty or 200 OK responses with no content
  const contentType = response.headers.get("content-type");
  if (contentType && contentType.includes("application/json")) {
    return response.json() as Promise<T>;
  }
  return null as unknown as T;
}

export const api = {
  alumnos: {
    getAll: (search?: string, dni?: string) => {
      const params = new URLSearchParams();
      if (search) params.append("search", search);
      if (dni) params.append("dni", dni);
      const query = params.toString() ? `?${params.toString()}` : "";
      return request<Alumno[]>(`/alumnos${query}`);
    },
    getById: (id: number) => request<Alumno>(`/alumnos/${id}`),
    create: (data: Alumno) => request<Alumno>("/alumnos", { method: "POST", body: JSON.stringify(data) }),
    update: (id: number, data: Alumno) => request<Alumno>(`/alumnos/${id}`, { method: "PUT", body: JSON.stringify(data) }),
    delete: (id: number) => request<void>(`/alumnos/${id}`, { method: "DELETE" }),
  },
  docentes: {
    getAll: (search?: string, dni?: string) => {
      const params = new URLSearchParams();
      if (search) params.append("search", search);
      if (dni) params.append("dni", dni);
      const query = params.toString() ? `?${params.toString()}` : "";
      return request<Docente[]>(`/docentes${query}`);
    },
    getById: (id: number) => request<Docente>(`/docentes/${id}`),
    create: (data: Docente) => request<Docente>("/docentes", { method: "POST", body: JSON.stringify(data) }),
    update: (id: number, data: Docente) => request<Docente>(`/docentes/${id}`, { method: "PUT", body: JSON.stringify(data) }),
    delete: (id: number) => request<void>(`/docentes/${id}`, { method: "DELETE" }),
  },
  carreras: {
    getAll: (search?: string) => {
      const query = search ? `?search=${encodeURIComponent(search)}` : "";
      return request<Carrera[]>(`/carreras${query}`);
    },
    getById: (id: number) => request<Carrera>(`/carreras/${id}`),
    create: (data: Carrera) => request<Carrera>("/carreras", { method: "POST", body: JSON.stringify(data) }),
    update: (id: number, data: Carrera) => request<Carrera>(`/carreras/${id}`, { method: "PUT", body: JSON.stringify(data) }),
    delete: (id: number) => request<void>(`/carreras/${id}`, { method: "DELETE" }),
  },
  cohortes: {
    getAll: (search?: string) => {
      const query = search ? `?search=${encodeURIComponent(search)}` : "";
      return request<Cohorte[]>(`/cohortes${query}`);
    },
    getById: (id: number) => request<Cohorte>(`/cohortes/${id}`),
    getInscripciones: (id: number) => request<InscripcionAlumnos[]>(`/cohortes/${id}/inscripciones`),
    create: (data: Cohorte) => request<Cohorte>("/cohortes", { method: "POST", body: JSON.stringify(data) }),
    update: (id: number, data: Cohorte) => request<Cohorte>(`/cohortes/${id}`, { method: "PUT", body: JSON.stringify(data) }),
    delete: (id: number) => request<void>(`/cohortes/${id}`, { method: "DELETE" }),
  },
  inscripciones: {
    getAll: (dni?: string, cohorteId?: number) => {
      const params = new URLSearchParams();
      if (dni) params.append("dni", dni);
      if (cohorteId) params.append("cohorteId", cohorteId.toString());
      const query = params.toString() ? `?${params.toString()}` : "";
      return request<InscripcionAlumnos[]>(`/inscripciones${query}`);
    },
    getById: (id: number) => request<InscripcionAlumnos>(`/inscripciones/${id}`),
    create: (data: InscripcionAlumnos) => request<InscripcionAlumnos>("/inscripciones", { method: "POST", body: JSON.stringify(data) }),
    update: (id: number, data: InscripcionAlumnos) => request<InscripcionAlumnos>(`/inscripciones/${id}`, { method: "PUT", body: JSON.stringify(data) }),
    delete: (id: number) => request<void>(`/inscripciones/${id}`, { method: "DELETE" }),
  },
  ingresos: {
    getAll: (dni?: string, fechaIni?: string, fechaFin?: string) => {
      const params = new URLSearchParams();
      if (dni) params.append("dni", dni);
      if (fechaIni) params.append("fechaIni", fechaIni);
      if (fechaFin) params.append("fechaFin", fechaFin);
      const query = params.toString() ? `?${params.toString()}` : "";
      return request<Ingreso[]>(`/ingresos${query}`);
    },
    getById: (id: number) => request<Ingreso>(`/ingresos/${id}`),
    create: (data: Ingreso) => request<Ingreso>("/ingresos", { method: "POST", body: JSON.stringify(data) }),
    update: (id: number, data: Ingreso) => request<Ingreso>(`/ingresos/${id}`, { method: "PUT", body: JSON.stringify(data) }),
    anular: (id: number, usuario: string = "admin") => request<void>(`/ingresos/${id}/anular?usuario=${encodeURIComponent(usuario)}`, { method: "POST" }),
  },
};
