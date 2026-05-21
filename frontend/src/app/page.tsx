"use client";

import React, { useState, useEffect } from "react";
import {
  api,
  Alumno,
  Docente,
  Carrera,
  Cohorte,
  Ingreso,
  InscripcionAlumnos,
} from "@/services/api";
import {
  Users,
  GraduationCap,
  BookOpen,
  Calendar,
  DollarSign,
  Search,
  Plus,
  Edit2,
  Trash2,
  AlertTriangle,
  CheckCircle,
  XCircle,
  Info,
  Menu,
  X,
  CreditCard,
} from "lucide-react";

type Section = "dashboard" | "alumnos" | "docentes" | "carreras" | "cohortes" | "ingresos" | "inscripciones";

export default function DashboardPage() {
  const [currentSection, setCurrentSection] = useState<Section>("dashboard");
  const [sidebarOpen, setSidebarOpen] = useState(true);

  // Statistics
  const [stats, setStats] = useState({
    alumnos: 0,
    docentes: 0,
    carreras: 0,
    ingresosTotales: 0,
  });

  // Data lists
  const [alumnos, setAlumnos] = useState<Alumno[]>([]);
  const [docentes, setDocentes] = useState<Docente[]>([]);
  const [carreras, setCarreras] = useState<Carrera[]>([]);
  const [cohortes, setCohortes] = useState<Cohorte[]>([]);
  const [ingresos, setIngresos] = useState<Ingreso[]>([]);

  // Search queries
  const [searchQuery, setSearchQuery] = useState("");
  const [dniQuery, setDniQuery] = useState("");

  // Loading states
  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  // Modals state
  const [modalType, setModalType] = useState<"create" | "edit" | "delete" | "view" | null>(null);
  const [targetEntity, setTargetEntity] = useState<any>(null);

  // Form Fields
  const [formAlumno, setFormAlumno] = useState<Partial<Alumno>>({
    nombre: "",
    apellido: "",
    dni: "",
    cuil: "",
    sexo: "M",
    fechaNacimiento: "",
  });

  const [formDocente, setFormDocente] = useState<Partial<Docente>>({
    nombre: "",
    apellido: "",
    dni: "",
    cuil: "",
    sexo: "M",
    fechaNacimiento: "",
  });

  const [formCarrera, setFormCarrera] = useState<Partial<Carrera>>({
    descripcion: "",
    resolucion: "",
  });

  const [formCohorte, setFormCohorte] = useState({
    descripcion: "",
    carreraId: "",
  });

  const [formIngreso, setFormIngreso] = useState({
    fechaPago: new Date().toISOString().split("T")[0],
    importe: 0,
    numeroRecibo: 0,
    alumnoId: "",
    cuentaId: "1",
    tipoIngresoId: "1",
    concepto: "",
  });

  const [formInscripcion, setFormInscripcion] = useState({
    alumnoId: "",
    cohorteId: "",
    fechaInscripcion: new Date().toISOString().split("T")[0],
  });

  // Fetch initial data
  useEffect(() => {
    loadDashboardData();
  }, []);

  const showSuccess = (msg: string) => {
    setSuccessMsg(msg);
    setTimeout(() => setSuccessMsg(""), 3000);
  };

  const showError = (msg: string) => {
    setErrorMsg(msg);
    setTimeout(() => setErrorMsg(""), 4000);
  };

  const loadDashboardData = async () => {
    setLoading(true);
    try {
      const [allAlumnos, allDocentes, allCarreras, allIngresos, allCohortes] = await Promise.all([
        api.alumnos.getAll().catch(() => []),
        api.docentes.getAll().catch(() => []),
        api.carreras.getAll().catch(() => []),
        api.ingresos.getAll().catch(() => []),
        api.cohortes.getAll().catch(() => []),
      ]);

      setAlumnos(allAlumnos);
      setDocentes(allDocentes);
      setCarreras(allCarreras);
      setIngresos(allIngresos);
      setCohortes(allCohortes);

      const totalRevenue = allIngresos
        .filter((i) => !i.anulado)
        .reduce((sum, item) => sum + item.importe, 0);

      setStats({
        alumnos: allAlumnos.length,
        docentes: allDocentes.length,
        carreras: allCarreras.length,
        ingresosTotales: totalRevenue,
      });
    } catch (err: any) {
      showError("Error al cargar datos del backend.");
    } finally {
      setLoading(false);
    }
  };

  // Search logic
  const handleSearch = async () => {
    setLoading(true);
    try {
      if (currentSection === "alumnos") {
        const res = await api.alumnos.getAll(searchQuery, dniQuery);
        setAlumnos(res);
      } else if (currentSection === "docentes") {
        const res = await api.docentes.getAll(searchQuery, dniQuery);
        setDocentes(res);
      } else if (currentSection === "ingresos") {
        const res = await api.ingresos.getAll(dniQuery || undefined);
        setIngresos(res);
      }
    } catch (err: any) {
      showError("No se encontraron resultados para la búsqueda.");
    } finally {
      setLoading(false);
    }
  };

  // CRUD Operations
  const handleSaveAlumno = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (modalType === "create") {
        await api.alumnos.create(formAlumno as Alumno);
        showSuccess("Alumno creado correctamente");
      } else if (modalType === "edit" && targetEntity?.id) {
        await api.alumnos.update(targetEntity.id, formAlumno as Alumno);
        showSuccess("Alumno editado correctamente");
      }
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al guardar Alumno");
    }
  };

  const handleSaveDocente = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (modalType === "create") {
        await api.docentes.create(formDocente as Docente);
        showSuccess("Docente creado correctamente");
      } else if (modalType === "edit" && targetEntity?.id) {
        await api.docentes.update(targetEntity.id, formDocente as Docente);
        showSuccess("Docente editado correctamente");
      }
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al guardar Docente");
    }
  };

  const handleSaveCarrera = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (modalType === "create") {
        await api.carreras.create(formCarrera as Carrera);
        showSuccess("Carrera creada correctamente");
      } else if (modalType === "edit" && targetEntity?.id) {
        await api.carreras.update(targetEntity.id, formCarrera as Carrera);
        showSuccess("Carrera editada correctamente");
      }
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al guardar Carrera");
    }
  };

  const handleSaveCohorte = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const carrera = carreras.find((c) => c.id === parseInt(formCohorte.carreraId));
      if (!carrera) throw new Error("Carrera no válida");

      const cohortePayload = {
        descripcion: formCohorte.descripcion,
        carrera: carrera,
      };

      if (modalType === "create") {
        await api.cohortes.create(cohortePayload as Cohorte);
        showSuccess("Cohorte creada correctamente");
      }
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al guardar Cohorte");
    }
  };

  const handleSaveIngreso = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const alumno = alumnos.find((a) => a.id === parseInt(formIngreso.alumnoId));
      if (!alumno) throw new Error("Debe seleccionar un alumno");

      const ingresoPayload = {
        fechaPago: formIngreso.fechaPago,
        importe: Number(formIngreso.importe),
        numeroRecibo: Number(formIngreso.numeroRecibo),
        anulado: false,
        concepto: formIngreso.concepto,
        alumno: alumno,
        cuenta: { id: parseInt(formIngreso.cuentaId), descripcion: "Cuenta Principal" },
        tipoIngreso: { id: parseInt(formIngreso.tipoIngresoId), descripcion: "Cuota" },
      };

      await api.ingresos.create(ingresoPayload as any);
      showSuccess("Pago registrado correctamente");
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al guardar Pago");
    }
  };

  const handleAnularIngreso = async (id: number) => {
    try {
      await api.ingresos.anular(id);
      showSuccess("Recibo de pago anulado correctamente");
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al anular recibo");
    }
  };

  const handleDeleteEntity = async () => {
    if (!targetEntity?.id) return;
    try {
      if (currentSection === "alumnos") {
        await api.alumnos.delete(targetEntity.id);
        showSuccess("Alumno eliminado correctamente");
      } else if (currentSection === "docentes") {
        await api.docentes.delete(targetEntity.id);
        showSuccess("Docente eliminado correctamente");
      } else if (currentSection === "carreras") {
        await api.carreras.delete(targetEntity.id);
        showSuccess("Carrera eliminada correctamente");
      } else if (currentSection === "cohortes") {
        await api.cohortes.delete(targetEntity.id);
        showSuccess("Cohorte eliminada correctamente");
      }
      setModalType(null);
      loadDashboardData();
    } catch (err: any) {
      showError(err.message || "Error al eliminar elemento");
    }
  };

  // Open creation/edit forms
  const openCreateModal = () => {
    setModalType("create");
    setTargetEntity(null);
    if (currentSection === "alumnos") {
      setFormAlumno({ nombre: "", apellido: "", dni: "", cuil: "", sexo: "M", fechaNacimiento: "" });
    } else if (currentSection === "docentes") {
      setFormDocente({ nombre: "", apellido: "", dni: "", cuil: "", sexo: "M", fechaNacimiento: "" });
    } else if (currentSection === "carreras") {
      setFormCarrera({ descripcion: "", resolucion: "" });
    } else if (currentSection === "cohortes") {
      setFormCohorte({ descripcion: "", carreraId: carreras[0]?.id?.toString() || "" });
    } else if (currentSection === "ingresos") {
      setFormIngreso({
        fechaPago: new Date().toISOString().split("T")[0],
        importe: 0,
        numeroRecibo: 0,
        alumnoId: alumnos[0]?.id?.toString() || "",
        cuentaId: "1",
        tipoIngresoId: "1",
        concepto: "",
      });
    }
  };

  const openEditModal = (entity: any) => {
    setModalType("edit");
    setTargetEntity(entity);
    if (currentSection === "alumnos") {
      setFormAlumno({ ...entity });
    } else if (currentSection === "docentes") {
      setFormDocente({ ...entity });
    } else if (currentSection === "carreras") {
      setFormCarrera({ ...entity });
    }
  };

  return (
    <div className="flex h-screen bg-[#0f172a] text-[#f8fafc] font-sans overflow-hidden">
      {/* Notifications */}
      {successMsg && (
        <div className="fixed top-4 right-4 z-50 flex items-center gap-2 bg-emerald-500/90 text-white px-4 py-3 rounded-lg shadow-xl backdrop-blur-md transition-all duration-300 border border-emerald-400">
          <CheckCircle className="w-5 h-5" />
          <span className="font-semibold text-sm">{successMsg}</span>
        </div>
      )}
      {errorMsg && (
        <div className="fixed top-4 right-4 z-50 flex items-center gap-2 bg-rose-500/90 text-white px-4 py-3 rounded-lg shadow-xl backdrop-blur-md transition-all duration-300 border border-rose-400">
          <XCircle className="w-5 h-5" />
          <span className="font-semibold text-sm">{errorMsg}</span>
        </div>
      )}

      {/* Sidebar navigation */}
      <aside
        className={`${
          sidebarOpen ? "w-64" : "w-20"
        } bg-[#1e293b] border-r border-[#334155] transition-all duration-300 flex flex-col z-20`}
      >
        <div className="h-16 flex items-center justify-between px-6 border-b border-[#334155]">
          {sidebarOpen ? (
            <span className="text-xl font-bold bg-gradient-to-r from-violet-400 to-indigo-400 bg-clip-text text-transparent">
              HumaSpring
            </span>
          ) : (
            <span className="text-xl font-bold text-violet-400">HS</span>
          )}
          <button
            onClick={() => setSidebarOpen(!sidebarOpen)}
            className="p-1 rounded hover:bg-[#334155] text-[#94a3b8] hover:text-white"
          >
            <Menu className="w-5 h-5" />
          </button>
        </div>

        <nav className="flex-1 px-4 py-6 space-y-2 overflow-y-auto">
          {[
            { id: "dashboard", label: "Overview", icon: Users },
            { id: "alumnos", label: "Alumnos", icon: GraduationCap },
            { id: "docentes", label: "Docentes", icon: Users },
            { id: "carreras", label: "Carreras", icon: BookOpen },
            { id: "cohortes", label: "Cohortes", icon: Calendar },
            { id: "ingresos", label: "Pagos & Caja", icon: DollarSign },
          ].map((item) => {
            const Icon = item.icon;
            const active = currentSection === item.id;
            return (
              <button
                key={item.id}
                onClick={() => {
                  setCurrentSection(item.id as Section);
                  setSearchQuery("");
                  setDniQuery("");
                }}
                className={`w-full flex items-center gap-4 px-4 py-3 rounded-lg font-medium text-sm transition-all ${
                  active
                    ? "bg-violet-600 text-white shadow-md shadow-violet-500/20"
                    : "text-[#94a3b8] hover:bg-[#334155] hover:text-white"
                }`}
              >
                <Icon className="w-5 h-5 flex-shrink-0" />
                {sidebarOpen && <span>{item.label}</span>}
              </button>
            );
          })}
        </nav>
      </aside>

      {/* Main Workspace */}
      <main className="flex-1 flex flex-col overflow-hidden bg-gradient-to-br from-[#0f172a] via-[#1e1b4b] to-[#0f172a]">
        {/* Top Header */}
        <header className="h-16 border-b border-[#334155] bg-[#0f172a]/40 backdrop-blur-md flex items-center justify-between px-8">
          <div className="flex items-center gap-4">
            <h1 className="text-xl font-bold capitalize text-white">
              {currentSection === "dashboard" ? "Dashboard General" : currentSection}
            </h1>
            {loading && <span className="animate-spin rounded-full h-4 w-4 border-2 border-violet-500 border-t-transparent" />}
          </div>
          <div className="flex items-center gap-4">
            <button
              onClick={loadDashboardData}
              className="px-3 py-1.5 rounded-lg text-xs font-semibold bg-[#1e293b] border border-[#334155] text-[#94a3b8] hover:text-white hover:bg-[#334155] transition"
            >
              Sincronizar
            </button>
            <div className="flex items-center gap-2">
              <div className="w-8 h-8 rounded-full bg-violet-600 flex items-center justify-center font-bold text-sm text-white">
                AD
              </div>
              <span className="text-sm font-semibold text-[#cbd5e1] hidden md:inline">Administrador</span>
            </div>
          </div>
        </header>

        {/* Content Body */}
        <div className="flex-1 overflow-y-auto p-8">
          {currentSection === "dashboard" && (
            <div className="space-y-8 animate-fade-in">
              {/* Counter Grid */}
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                {[
                  { label: "Total Alumnos", value: stats.alumnos, icon: GraduationCap, color: "from-blue-500 to-indigo-500" },
                  { label: "Total Docentes", value: stats.docentes, icon: Users, color: "from-violet-500 to-purple-500" },
                  { label: "Carreras Activas", value: stats.carreras, icon: BookOpen, color: "from-pink-500 to-rose-500" },
                  { label: "Caja Cobrada", value: `$${stats.ingresosTotales.toLocaleString()}`, icon: DollarSign, color: "from-emerald-500 to-teal-500" },
                ].map((stat, idx) => {
                  const Icon = stat.icon;
                  return (
                    <div
                      key={idx}
                      className="bg-[#1e293b]/70 border border-[#334155]/60 rounded-2xl p-6 flex items-center justify-between shadow-lg relative overflow-hidden group hover:scale-[1.02] transition-transform duration-300"
                    >
                      <div className="absolute top-0 right-0 w-24 h-24 bg-gradient-to-br from-white/5 to-white/0 rounded-full blur-xl group-hover:scale-125 transition-transform" />
                      <div className="space-y-2">
                        <span className="text-sm font-medium text-[#94a3b8]">{stat.label}</span>
                        <div className="text-3xl font-bold text-white">{stat.value}</div>
                      </div>
                      <div className={`p-4 rounded-xl bg-gradient-to-br ${stat.color} text-white shadow-lg`}>
                        <Icon className="w-6 h-6" />
                      </div>
                    </div>
                  );
                })}
              </div>

              {/* Quick Actions & Recent box */}
              <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                {/* Visual Graphic Representation using SVG */}
                <div className="lg:col-span-2 bg-[#1e293b]/60 border border-[#334155]/60 rounded-2xl p-6 shadow-xl">
                  <h3 className="text-lg font-bold text-white mb-6">Tendencia de Cobros de Caja</h3>
                  <div className="h-64 flex items-end justify-between gap-2 px-4 relative">
                    <svg className="absolute inset-0 w-full h-full" viewBox="0 0 100 100" preserveAspectRatio="none">
                      <defs>
                        <linearGradient id="chartGrad" x1="0" y1="0" x2="0" y2="1">
                          <stop offset="0%" stopColor="rgb(139, 92, 246)" stopOpacity="0.4" />
                          <stop offset="100%" stopColor="rgb(139, 92, 246)" stopOpacity="0.0" />
                        </linearGradient>
                      </defs>
                      <path
                        d="M 0 80 Q 20 40 40 60 T 80 20 T 100 30 L 100 100 L 0 100 Z"
                        fill="url(#chartGrad)"
                        className="transition-all duration-1000"
                      />
                      <path
                        d="M 0 80 Q 20 40 40 60 T 80 20 T 100 30"
                        fill="none"
                        stroke="rgb(139, 92, 246)"
                        strokeWidth="2.5"
                        className="transition-all duration-1000"
                      />
                    </svg>
                    <div className="absolute top-2 left-4 text-xs font-semibold text-violet-400">Total Recaudado: ${stats.ingresosTotales}</div>
                    <div className="absolute bottom-2 right-4 text-xs text-[#94a3b8]">Actualizado en tiempo real</div>
                  </div>
                </div>

                <div className="bg-[#1e293b]/60 border border-[#334155]/60 rounded-2xl p-6 shadow-xl flex flex-col justify-between">
                  <div>
                    <h3 className="text-lg font-bold text-white mb-4">Accesos Rápidos</h3>
                    <div className="space-y-3">
                      <button
                        onClick={() => { setCurrentSection("alumnos"); openCreateModal(); }}
                        className="w-full flex items-center justify-between p-3 rounded-xl bg-violet-600/10 hover:bg-violet-600/20 border border-violet-500/20 text-violet-300 font-semibold text-sm transition"
                      >
                        <span>Registrar Nuevo Alumno</span>
                        <Plus className="w-4 h-4" />
                      </button>
                      <button
                        onClick={() => { setCurrentSection("ingresos"); openCreateModal(); }}
                        className="w-full flex items-center justify-between p-3 rounded-xl bg-emerald-600/10 hover:bg-emerald-600/20 border border-emerald-500/20 text-emerald-300 font-semibold text-sm transition"
                      >
                        <span>Cobrar Nueva Cuota</span>
                        <Plus className="w-4 h-4" />
                      </button>
                      <button
                        onClick={() => { setCurrentSection("carreras"); openCreateModal(); }}
                        className="w-full flex items-center justify-between p-3 rounded-xl bg-blue-600/10 hover:bg-blue-600/20 border border-blue-500/20 text-blue-300 font-semibold text-sm transition"
                      >
                        <span>Agregar Nueva Carrera</span>
                        <Plus className="w-4 h-4" />
                      </button>
                    </div>
                  </div>
                  <div className="pt-6 border-t border-[#334155]/60 mt-6">
                    <span className="text-xs text-[#94a3b8] block">Sistema de Gestión de Alumnos y Caja Académica - Universidad Nacional</span>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* LISTS AND CRUD SECTIONS */}
          {currentSection !== "dashboard" && (
            <div className="bg-[#1e293b]/50 border border-[#334155]/60 rounded-2xl shadow-xl overflow-hidden animate-fade-in">
              {/* Toolbar */}
              <div className="p-6 border-b border-[#334155]/60 flex flex-col md:flex-row gap-4 items-center justify-between">
                <div className="flex flex-1 gap-4 w-full md:max-w-md">
                  {(currentSection === "alumnos" || currentSection === "docentes" || currentSection === "ingresos") && (
                    <>
                      <div className="relative flex-1">
                        <Search className="w-4 h-4 text-[#94a3b8] absolute left-3 top-3" />
                        <input
                          type="text"
                          placeholder="Buscar por nombre..."
                          value={searchQuery}
                          onChange={(e) => setSearchQuery(e.target.value)}
                          className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl py-2 pl-9 pr-4 text-sm text-white focus:outline-none focus:border-violet-500 transition"
                        />
                      </div>
                      <div className="relative flex-1">
                        <Search className="w-4 h-4 text-[#94a3b8] absolute left-3 top-3" />
                        <input
                          type="text"
                          placeholder="Buscar por DNI..."
                          value={dniQuery}
                          onChange={(e) => setDniQuery(e.target.value)}
                          className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl py-2 pl-9 pr-4 text-sm text-white focus:outline-none focus:border-violet-500 transition"
                        />
                      </div>
                      <button
                        onClick={handleSearch}
                        className="px-4 py-2 bg-violet-600 hover:bg-violet-500 text-white font-semibold text-sm rounded-xl transition"
                      >
                        Filtrar
                      </button>
                    </>
                  )}
                </div>
                <button
                  onClick={openCreateModal}
                  className="w-full md:w-auto flex items-center justify-center gap-2 px-5 py-2.5 bg-gradient-to-r from-violet-600 to-indigo-600 hover:from-violet-500 hover:to-indigo-500 text-white font-bold text-sm rounded-xl shadow-lg shadow-indigo-500/20 transition"
                >
                  <Plus className="w-4 h-4" />
                  <span>Agregar {currentSection.slice(0, -1)}</span>
                </button>
              </div>

              {/* Data Table */}
              <div className="overflow-x-auto">
                <table className="w-full text-left border-collapse">
                  <thead>
                    <tr className="bg-[#0f172a]/40 text-[#94a3b8] font-bold text-xs uppercase tracking-wider border-b border-[#334155]/60">
                      {currentSection === "alumnos" && (
                        <>
                          <th className="p-4">DNI</th>
                          <th className="p-4">Apellido y Nombre</th>
                          <th className="p-4">CUIL</th>
                          <th className="p-4">Sexo</th>
                          <th className="p-4">Fecha Nacimiento</th>
                          <th className="p-4 text-right">Acciones</th>
                        </>
                      )}
                      {currentSection === "docentes" && (
                        <>
                          <th className="p-4">DNI</th>
                          <th className="p-4">Apellido y Nombre</th>
                          <th className="p-4">CUIL</th>
                          <th className="p-4">Sexo</th>
                          <th className="p-4 text-right">Acciones</th>
                        </>
                      )}
                      {currentSection === "carreras" && (
                        <>
                          <th className="p-4">ID</th>
                          <th className="p-4">Descripción / Nombre</th>
                          <th className="p-4">Resolución</th>
                          <th className="p-4 text-right">Acciones</th>
                        </>
                      )}
                      {currentSection === "cohortes" && (
                        <>
                          <th className="p-4">ID</th>
                          <th className="p-4">Descripción</th>
                          <th className="p-4">Carrera</th>
                          <th className="p-4 text-right">Acciones</th>
                        </>
                      )}
                      {currentSection === "ingresos" && (
                        <>
                          <th className="p-4">Recibo N°</th>
                          <th className="p-4">Fecha</th>
                          <th className="p-4">Alumno</th>
                          <th className="p-4">Concepto</th>
                          <th className="p-4">Importe</th>
                          <th className="p-4">Estado</th>
                          <th className="p-4 text-right">Acciones</th>
                        </>
                      )}
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-[#334155]/40 text-sm text-[#cbd5e1]">
                    {currentSection === "alumnos" &&
                      alumnos.map((item) => (
                        <tr key={item.id} className="hover:bg-[#1e293b]/40 transition">
                          <td className="p-4 font-mono font-medium">{item.dni}</td>
                          <td className="p-4 font-semibold text-white">{item.apellido}, {item.nombre}</td>
                          <td className="p-4">{item.cuil || "-"}</td>
                          <td className="p-4">{item.sexo}</td>
                          <td className="p-4">{item.fechaNacimiento ? new Date(item.fechaNacimiento).toLocaleDateString() : "-"}</td>
                          <td className="p-4 text-right">
                            <div className="flex items-center justify-end gap-2">
                              <button onClick={() => openEditModal(item)} className="p-1.5 rounded hover:bg-[#334155] text-indigo-400 hover:text-white transition">
                                <Edit2 className="w-4 h-4" />
                              </button>
                              <button onClick={() => { setModalType("delete"); setTargetEntity(item); }} className="p-1.5 rounded hover:bg-[#334155] text-rose-400 hover:text-white transition">
                                <Trash2 className="w-4 h-4" />
                              </button>
                            </div>
                          </td>
                        </tr>
                      ))}

                    {currentSection === "docentes" &&
                      docentes.map((item) => (
                        <tr key={item.id} className="hover:bg-[#1e293b]/40 transition">
                          <td className="p-4 font-mono font-medium">{item.dni}</td>
                          <td className="p-4 font-semibold text-white">{item.apellido}, {item.nombre}</td>
                          <td className="p-4">{item.cuil || "-"}</td>
                          <td className="p-4">{item.sexo}</td>
                          <td className="p-4 text-right">
                            <div className="flex items-center justify-end gap-2">
                              <button onClick={() => openEditModal(item)} className="p-1.5 rounded hover:bg-[#334155] text-indigo-400 hover:text-white transition">
                                <Edit2 className="w-4 h-4" />
                              </button>
                              <button onClick={() => { setModalType("delete"); setTargetEntity(item); }} className="p-1.5 rounded hover:bg-[#334155] text-rose-400 hover:text-white transition">
                                <Trash2 className="w-4 h-4" />
                              </button>
                            </div>
                          </td>
                        </tr>
                      ))}

                    {currentSection === "carreras" &&
                      carreras.map((item) => (
                        <tr key={item.id} className="hover:bg-[#1e293b]/40 transition">
                          <td className="p-4 font-mono text-[#94a3b8]">{item.id}</td>
                          <td className="p-4 font-semibold text-white">{item.descripcion}</td>
                          <td className="p-4 font-mono text-xs">{item.resolucion || "-"}</td>
                          <td className="p-4 text-right">
                            <div className="flex items-center justify-end gap-2">
                              <button onClick={() => openEditModal(item)} className="p-1.5 rounded hover:bg-[#334155] text-indigo-400 hover:text-white transition">
                                <Edit2 className="w-4 h-4" />
                              </button>
                              <button onClick={() => { setModalType("delete"); setTargetEntity(item); }} className="p-1.5 rounded hover:bg-[#334155] text-rose-400 hover:text-white transition">
                                <Trash2 className="w-4 h-4" />
                              </button>
                            </div>
                          </td>
                        </tr>
                      ))}

                    {currentSection === "cohortes" &&
                      cohortes.map((item) => (
                        <tr key={item.id} className="hover:bg-[#1e293b]/40 transition">
                          <td className="p-4 font-mono text-[#94a3b8]">{item.id}</td>
                          <td className="p-4 font-semibold text-white">{item.descripcion}</td>
                          <td className="p-4">{item.carrera?.descripcion || "-"}</td>
                          <td className="p-4 text-right">
                            <div className="flex items-center justify-end gap-2">
                              <button onClick={() => { setModalType("delete"); setTargetEntity(item); }} className="p-1.5 rounded hover:bg-[#334155] text-rose-400 hover:text-white transition">
                                <Trash2 className="w-4 h-4" />
                              </button>
                            </div>
                          </td>
                        </tr>
                      ))}

                    {currentSection === "ingresos" &&
                      ingresos.map((item) => (
                        <tr key={item.id} className="hover:bg-[#1e293b]/40 transition">
                          <td className="p-4 font-mono font-medium text-white">{item.numeroRecibo}</td>
                          <td className="p-4">{new Date(item.fechaPago).toLocaleDateString()}</td>
                          <td className="p-4">
                            {item.alumno ? `${item.alumno.apellido}, ${item.alumno.nombre}` : "-"}
                          </td>
                          <td className="p-4 italic text-[#94a3b8]">{item.concepto || "Cuota Académica"}</td>
                          <td className="p-4 font-bold text-white">${item.importe.toLocaleString()}</td>
                          <td className="p-4">
                            {item.anulado ? (
                              <span className="px-2 py-1 text-xs font-semibold bg-rose-500/10 text-rose-400 border border-rose-500/20 rounded-full">
                                Anulado
                              </span>
                            ) : (
                              <span className="px-2 py-1 text-xs font-semibold bg-emerald-500/10 text-emerald-400 border border-emerald-500/20 rounded-full">
                                Activo
                              </span>
                            )}
                          </td>
                          <td className="p-4 text-right">
                            {!item.anulado && (
                              <button
                                onClick={() => handleAnularIngreso(item.id!)}
                                className="px-2.5 py-1 text-xs font-semibold bg-rose-600 hover:bg-rose-500 text-white rounded-lg transition"
                              >
                                Anular
                              </button>
                            )}
                          </td>
                        </tr>
                      ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}
        </div>
      </main>

      {/* CRUD DIALOG MODAL */}
      {modalType && (
        <div className="fixed inset-0 z-50 bg-[#020617]/80 backdrop-blur-sm flex items-center justify-center p-4">
          <div className="bg-[#1e293b] border border-[#334155] rounded-2xl w-full max-w-lg shadow-2xl overflow-hidden animate-zoom-in">
            <div className="px-6 py-4 border-b border-[#334155] flex items-center justify-between">
              <h3 className="text-lg font-bold text-white capitalize">
                {modalType === "create" ? "Agregar" : modalType === "edit" ? "Editar" : "Eliminar"} {currentSection.slice(0, -1)}
              </h3>
              <button onClick={() => setModalType(null)} className="p-1 rounded hover:bg-[#334155] text-[#94a3b8] hover:text-white">
                <X className="w-5 h-5" />
              </button>
            </div>

            {modalType === "delete" ? (
              <div className="p-6 space-y-6">
                <div className="flex items-center gap-4 text-rose-400">
                  <AlertTriangle className="w-12 h-12 flex-shrink-0" />
                  <div>
                    <h4 className="font-bold text-white">¿Estás seguro de eliminar este elemento?</h4>
                    <p className="text-sm text-[#94a3b8] mt-1">Esta acción no se puede deshacer y puede afectar registros relacionados.</p>
                  </div>
                </div>
                <div className="flex justify-end gap-3">
                  <button onClick={() => setModalType(null)} className="px-4 py-2 bg-[#334155] hover:bg-[#475569] text-white font-semibold rounded-xl text-sm transition">
                    Cancelar
                  </button>
                  <button onClick={handleDeleteEntity} className="px-4 py-2 bg-rose-600 hover:bg-rose-500 text-white font-semibold rounded-xl text-sm transition">
                    Eliminar definitivamente
                  </button>
                </div>
              </div>
            ) : (
              <form
                onSubmit={
                  currentSection === "alumnos"
                    ? handleSaveAlumno
                    : currentSection === "docentes"
                    ? handleSaveDocente
                    : currentSection === "carreras"
                    ? handleSaveCarrera
                    : currentSection === "cohortes"
                    ? handleSaveCohorte
                    : handleSaveIngreso
                }
                className="p-6 space-y-4"
              >
                {/* ALUMNO FORM FIELDS */}
                {currentSection === "alumnos" && (
                  <div className="grid grid-cols-2 gap-4">
                    <div className="col-span-2">
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Apellido</label>
                      <input
                        type="text"
                        required
                        value={formAlumno.apellido}
                        onChange={(e) => setFormAlumno({ ...formAlumno, apellido: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div className="col-span-2">
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Nombre</label>
                      <input
                        type="text"
                        required
                        value={formAlumno.nombre}
                        onChange={(e) => setFormAlumno({ ...formAlumno, nombre: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">DNI</label>
                      <input
                        type="text"
                        required
                        value={formAlumno.dni}
                        onChange={(e) => setFormAlumno({ ...formAlumno, dni: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">CUIL</label>
                      <input
                        type="text"
                        value={formAlumno.cuil || ""}
                        onChange={(e) => setFormAlumno({ ...formAlumno, cuil: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Sexo</label>
                      <select
                        value={formAlumno.sexo}
                        onChange={(e) => setFormAlumno({ ...formAlumno, sexo: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      >
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                      </select>
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">F. Nacimiento</label>
                      <input
                        type="date"
                        value={formAlumno.fechaNacimiento}
                        onChange={(e) => setFormAlumno({ ...formAlumno, fechaNacimiento: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                  </div>
                )}

                {/* DOCENTE FORM FIELDS */}
                {currentSection === "docentes" && (
                  <div className="grid grid-cols-2 gap-4">
                    <div className="col-span-2">
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Apellido</label>
                      <input
                        type="text"
                        required
                        value={formDocente.apellido}
                        onChange={(e) => setFormDocente({ ...formDocente, apellido: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div className="col-span-2">
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Nombre</label>
                      <input
                        type="text"
                        required
                        value={formDocente.nombre}
                        onChange={(e) => setFormDocente({ ...formDocente, nombre: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">DNI</label>
                      <input
                        type="text"
                        required
                        value={formDocente.dni}
                        onChange={(e) => setFormDocente({ ...formDocente, dni: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">CUIL</label>
                      <input
                        type="text"
                        value={formDocente.cuil || ""}
                        onChange={(e) => setFormDocente({ ...formDocente, cuil: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                  </div>
                )}

                {/* CARRERA FORM FIELDS */}
                {currentSection === "carreras" && (
                  <div className="space-y-4">
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Nombre / Descripción de la Carrera</label>
                      <input
                        type="text"
                        required
                        value={formCarrera.descripcion}
                        onChange={(e) => setFormCarrera({ ...formCarrera, descripcion: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Resolución Ministerial / Expediente</label>
                      <input
                        type="text"
                        value={formCarrera.resolucion || ""}
                        onChange={(e) => setFormCarrera({ ...formCarrera, resolucion: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                  </div>
                )}

                {/* COHORTE FORM FIELDS */}
                {currentSection === "cohortes" && (
                  <div className="space-y-4">
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Nombre del Cohorte (Ej: 2026)</label>
                      <input
                        type="text"
                        required
                        value={formCohorte.descripcion}
                        onChange={(e) => setFormCohorte({ ...formCohorte, descripcion: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Carrera Vinculada</label>
                      <select
                        value={formCohorte.carreraId}
                        onChange={(e) => setFormCohorte({ ...formCohorte, carreraId: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      >
                        {carreras.map((c) => (
                          <option key={c.id} value={c.id}>{c.descripcion}</option>
                        ))}
                      </select>
                    </div>
                  </div>
                )}

                {/* INGRESO (PAGO) FORM FIELDS */}
                {currentSection === "ingresos" && (
                  <div className="grid grid-cols-2 gap-4">
                    <div className="col-span-2">
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Alumno</label>
                      <select
                        value={formIngreso.alumnoId}
                        onChange={(e) => setFormIngreso({ ...formIngreso, alumnoId: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      >
                        {alumnos.map((a) => (
                          <option key={a.id} value={a.id}>{a.apellido}, {a.nombre} (DNI {a.dni})</option>
                        ))}
                      </select>
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Fecha de Pago</label>
                      <input
                        type="date"
                        required
                        value={formIngreso.fechaPago}
                        onChange={(e) => setFormIngreso({ ...formIngreso, fechaPago: e.target.value })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">N° de Recibo</label>
                      <input
                        type="number"
                        required
                        value={formIngreso.numeroRecibo}
                        onChange={(e) => setFormIngreso({ ...formIngreso, numeroRecibo: parseInt(e.target.value) || 0 })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Importe ($)</label>
                      <input
                        type="number"
                        required
                        value={formIngreso.importe}
                        onChange={(e) => setFormIngreso({ ...formIngreso, importe: parseFloat(e.target.value) || 0 })}
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                    <div>
                      <label className="text-xs font-semibold text-[#94a3b8] uppercase">Concepto / Comentario</label>
                      <input
                        type="text"
                        value={formIngreso.concepto}
                        onChange={(e) => setFormIngreso({ ...formIngreso, concepto: e.target.value })}
                        placeholder="Ej. Matrícula 2026"
                        className="w-full bg-[#0f172a]/60 border border-[#334155] rounded-xl px-3 py-2 mt-1 text-white focus:outline-none focus:border-violet-500 transition"
                      />
                    </div>
                  </div>
                )}

                <div className="flex justify-end gap-3 pt-6 border-t border-[#334155] mt-6">
                  <button type="button" onClick={() => setModalType(null)} className="px-4 py-2 bg-[#334155] hover:bg-[#475569] text-white font-semibold rounded-xl text-sm transition">
                    Cancelar
                  </button>
                  <button type="submit" className="px-5 py-2 bg-violet-600 hover:bg-violet-500 text-white font-bold rounded-xl text-sm shadow-lg shadow-violet-500/25 transition">
                    Guardar Cambios
                  </button>
                </div>
              </form>
            )}
          </div>
        </div>
      )}
    </div>
  );
}
