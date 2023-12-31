USE [master]
GO
/****** Object:  Database [duan1_spring2023]    Script Date: 30/10/2023 2:22:37 CH ******/
CREATE DATABASE [duan1_spring2023]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'duan1_spring2023', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\duan1_spring2023.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'duan1_spring2023_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\duan1_spring2023_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [duan1_spring2023] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [duan1_spring2023].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [duan1_spring2023] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [duan1_spring2023] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [duan1_spring2023] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [duan1_spring2023] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [duan1_spring2023] SET ARITHABORT OFF 
GO
ALTER DATABASE [duan1_spring2023] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [duan1_spring2023] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [duan1_spring2023] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [duan1_spring2023] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [duan1_spring2023] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [duan1_spring2023] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [duan1_spring2023] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [duan1_spring2023] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [duan1_spring2023] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [duan1_spring2023] SET  ENABLE_BROKER 
GO
ALTER DATABASE [duan1_spring2023] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [duan1_spring2023] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [duan1_spring2023] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [duan1_spring2023] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [duan1_spring2023] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [duan1_spring2023] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [duan1_spring2023] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [duan1_spring2023] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [duan1_spring2023] SET  MULTI_USER 
GO
ALTER DATABASE [duan1_spring2023] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [duan1_spring2023] SET DB_CHAINING OFF 
GO
ALTER DATABASE [duan1_spring2023] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [duan1_spring2023] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [duan1_spring2023] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [duan1_spring2023] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [duan1_spring2023] SET QUERY_STORE = OFF
GO
USE [duan1_spring2023]
GO
/****** Object:  Table [dbo].[CameraChiTiet]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CameraChiTiet](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CameraChinh] [int] NULL,
	[CameraPhu] [int] NULL,
	[CameraGocRong] [int] NULL,
	[CameraTele] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietPhieuBaoHanh]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuBaoHanh](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Imei] [nvarchar](15) NULL,
	[ThoiHanBaoHanh] [int] NULL,
	[NgayMuaHang] [date] NULL,
	[NgayHetHan] [date] NULL,
	[MoTa] [nvarchar](128) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DienThoai]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DienThoai](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaDT] [nvarchar](30) NULL,
	[TenDT] [nvarchar](128) NULL,
	[MoTa] [nvarchar](256) NULL,
	[DungLuongPin] [int] NULL,
	[Rom] [int] NULL,
	[Ram] [int] NULL,
	[Cpu] [nvarchar](64) NULL,
	[GiaNhap] [bigint] NULL,
	[GiaBan] [bigint] NULL,
	[SoLuongTonKho] [int] NULL,
	[HinhAnh] [nvarchar](128) NULL,
	[IdHang] [int] NULL,
	[IdDongSanPham] [int] NULL,
	[IdMauSac] [int] NULL,
	[IdHeDieuHanh] [int] NULL,
	[IdCameraChiTiet] [int] NULL,
	[IdManHinhChiTiet] [int] NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DongSanPham]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DongSanPham](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
	[IdHang] [int] NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HangDienThoai]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HangDienThoai](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HeDieuHanh]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HeDieuHanh](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](30) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[NgayTao] [datetime] NULL,
	[NgayThanhToan] [datetime] NULL,
	[TienGiam] [bigint] NULL,
	[TongTien] [bigint] NULL,
	[TienKhachDua] [bigint] NULL,
	[TienThua] [bigint] NULL,
	[TraGop] [bit] NULL,
	[TienTraTruoc] [bigint] NULL,
	[TienThieu] [bigint] NULL,
	[HinhThucThanhToan] [nvarchar](15) NULL,
	[IdNhanVien] [int] NULL,
	[IdKhachHang] [int] NULL,
	[IdPhieuGiamGia] [int] NULL,
	[MaHoaDon] [varchar](30) NULL,
	[MaGiaoDichChuyenKhoan] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IdHoaDon] [int] NULL,
	[IdImei] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[IMEI]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IMEI](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IMEI] [nvarchar](15) NULL,
	[IdDienThoai] [int] NULL,
	[TrangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[Sdt] [nvarchar](15) NULL,
	[GioiTinh] [bit] NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](50) NULL,
	[TrangThai] [bit] NULL,
	[IdTheTichDiem] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichSuTraGop]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichSuTraGop](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaLichSuTraGop] [nvarchar](20) NULL,
	[NgayThanhToan] [date] NULL,
	[TongTien] [bigint] NULL,
	[GhiChu] [nvarchar](128) NULL,
	[IdPhieuTraGop] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiBaoHanh]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiBaoHanh](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TenLoaiBH] [nvarchar](30) NULL,
	[DieuKienBaoHanh] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ManHinhChiTiet]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ManHinhChiTiet](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[KichThuocManHinh] [decimal](2, 1) NULL,
	[DoPhanGiai] [nvarchar](15) NULL,
	[LoaiManHinh] [nvarchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MauSac]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MauSac](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaMauSac] [nvarchar](10) NULL,
	[TenMauSac] [nvarchar](30) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[GioiTinh] [bit] NULL,
	[Sdt] [nvarchar](15) NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](128) NULL,
	[Email] [nvarchar](50) NULL,
	[ChucVu] [bit] NULL,
	[TrangThai] [bit] NULL,
	[HinhAnh] [nvarchar](128) NULL,
	[IdTaiKhoan] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuBaoHang_LoaiBaoHanh]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuBaoHang_LoaiBaoHanh](
	[IdPhieuBaoHanh] [int] NULL,
	[IdLoaiBaoHanh] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuBaoHanh]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuBaoHanh](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IdHoaDonCT] [int] NULL,
	[IdChiTietPhieuBH] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuGiamGia]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuGiamGia](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaPhieu] [nvarchar](20) NULL,
	[TenPhieu] [nvarchar](50) NULL,
	[IdPhieuChiTiet] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuGiamGiaChiTiet]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuGiamGiaChiTiet](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[NgayBatDau] [date] NULL,
	[NgayKetThuc] [date] NULL,
	[LuotSuDung] [int] NULL,
	[DienKien] [bigint] NULL,
	[GiaTri] [float] NULL,
	[TrangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuTraGop]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuTraGop](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaPhieu] [nvarchar](20) NULL,
	[TongPhaiTra] [bigint] NULL,
	[LaiSuat] [float] NULL,
	[KyHan] [int] NULL,
	[NgayTao] [date] NULL,
	[PhaiTra] [bigint] NULL,
	[TrangThai] [bit] NULL,
	[IdHoaDon] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TaiKhoan] [nvarchar](15) NULL,
	[MatKhau] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheTichDiem]    Script Date: 30/10/2023 2:22:37 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheTichDiem](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaThe] [nvarchar](15) NULL,
	[NgayKichHoat] [date] NULL,
	[SoDiem] [int] NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[CameraChiTiet] ON 

INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (1, 24, 24, 24, 24)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (2, 200, 12, 0, 0)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (4, 100, 100, 100, 100)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (6, 100, 100, 100, 100)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (7, 100, 100, 100, 100)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (8, 24, 24, 24, 24)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (9, 108, 2, 0, 0)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (10, 12, 12, 12, 0)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (12, 12, 12, 12, 12)
INSERT [dbo].[CameraChiTiet] ([ID], [CameraChinh], [CameraPhu], [CameraGocRong], [CameraTele]) VALUES (13, 108, 2, 0, 0)
SET IDENTITY_INSERT [dbo].[CameraChiTiet] OFF
GO
SET IDENTITY_INSERT [dbo].[ChiTietPhieuBaoHanh] ON 

INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (1, N'198767725109801', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (2, N'111111111122259', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (3, N'111111111122263', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (4, N'111111111122223', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (5, N'111111111122260', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (6, N'404040404000011', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (7, N'111111111122242', 12, CAST(N'2023-04-06' AS Date), CAST(N'2024-04-06' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (8, N'404040404000012', 12, CAST(N'2023-04-07' AS Date), CAST(N'2024-04-07' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (9, N'404040404000013', 12, CAST(N'2023-04-07' AS Date), CAST(N'2024-04-07' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (10, N'111111111122227', 12, CAST(N'2023-04-11' AS Date), CAST(N'2024-04-11' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (11, N'404040404000002', 12, CAST(N'2023-04-11' AS Date), CAST(N'2024-04-11' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (12, N'404040404000003', 12, CAST(N'2023-04-11' AS Date), CAST(N'2024-04-11' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (13, N'404040404000015', 12, CAST(N'2023-04-11' AS Date), CAST(N'2024-04-11' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (14, N'404040404000004', 12, CAST(N'2023-04-11' AS Date), CAST(N'2024-04-11' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (15, N'404040404000005', 12, CAST(N'2023-04-11' AS Date), CAST(N'2024-04-11' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (16, N'111111111122228', 12, CAST(N'2023-04-16' AS Date), CAST(N'2024-04-16' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (17, N'111111111122233', 12, CAST(N'2023-04-16' AS Date), CAST(N'2024-04-16' AS Date), N'...', 1)
INSERT [dbo].[ChiTietPhieuBaoHanh] ([ID], [Imei], [ThoiHanBaoHanh], [NgayMuaHang], [NgayHetHan], [MoTa], [TrangThai]) VALUES (18, N'111111111122229', 12, CAST(N'2023-04-16' AS Date), CAST(N'2024-04-16' AS Date), N'...', 1)
SET IDENTITY_INSERT [dbo].[ChiTietPhieuBaoHanh] OFF
GO
SET IDENTITY_INSERT [dbo].[DienThoai] ON 

INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (1, N'ip14pm', N'iPhone 14 Pro Max 128GB Chính Hãng', N'Dep cuc ki luon a', 4655, 128, 6, N'Apple A16 Bionic', 24000000, 29990000, 2, N'iPhone-14-Pro-Max-3.jpg', 1, 3, 5, 2, 1, 1, 1)
INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (2, N'SS0023U', N'Samsung S23 Ultra 12GB Chính hãng', N'Dep', 5210, 1024, 12, N'Qualcomm Snapdragon 8 Gen 2', 27000000, 34990000, 3, N'samsung-s23-ultra-chinh-hang.jpg', 2, 1, 5, 2, 2, 2, 1)
INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (6, N'samsungs22', N'Samsung Galaxy S22 Ultra (8GB - 128GB)', N'Dep lam ban oi!', 5000, 128, 8, N'Qualcomm Snapdragon 8 Gen 1 (4 nm)', 19990000, 25990000, 3, N'samsung-galaxy-s22-ultra.jpg', 2, 1, 2, 2, 7, 7, 1)
INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (7, N'ip13pm', N'iPhone 13 Pro Max 128GB | Chính hãng VN/A', N'Dep lam!', 4326, 128, 6, N'Apple A15', 21000000, 25990000, 1, N'iphone-13-pro-max.jpg', 1, 9, 6, 2, 8, 8, 1)
INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (8, N'opporeno8', N'OPPO Reno8 T 5G (8GB - 128GB)', N'Dep', 4825, 128, 8, N'Snapdragon 695 5G', 7740000, 9980000, 3, N'oppo-reno8-den.jpg', 4, 10, 8, 2, 9, 9, 1)
INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (9, N'ip11n64gb', N'iPhone 11 64GB I Chính hãng VN/A', N'Dep, hang moi ve, chat luong cao lam 1', 3110, 64, 4, N'A13 Bionic', 10890000, 14990000, 5, N'iphone-11-trang.jpg', 1, 13, 11, 2, 10, 10, 1)
INSERT [dbo].[DienThoai] ([ID], [MaDT], [TenDT], [MoTa], [DungLuongPin], [Rom], [Ram], [Cpu], [GiaNhap], [GiaBan], [SoLuongTonKho], [HinhAnh], [IdHang], [IdDongSanPham], [IdMauSac], [IdHeDieuHanh], [IdCameraChiTiet], [IdManHinhChiTiet], [TrangThai]) VALUES (11, N'ip12pro256', N'iPhone 12 Pro 256GB I Chính hãng VN/A', N'hang vn chat luong cuc cao', 3400, 256, 6, N'Apple A14 Bionic (5 nm)', 19990000, 24990000, 6, N'iphone-12-pro-xam.jpg', 1, 12, 12, 2, 12, 12, 1)
SET IDENTITY_INSERT [dbo].[DienThoai] OFF
GO
SET IDENTITY_INSERT [dbo].[DongSanPham] ON 

INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (1, N'Samsung S Series', 2, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (2, N'Samsung Note Series', 2, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (3, N'iPhone 14 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (4, N'iPhone SE Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (6, N'Samsung A Series', 2, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (9, N'iPhone 13 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (10, N'Oppo Reno', 4, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (11, N'Samsung Galaxy Z Series', 2, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (12, N'iPhone 12 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (13, N'iPhone 11 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (14, N'iPhone 8 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (15, N'iPhone 7 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (16, N'iPhone 6 Series', 1, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (17, N'Oppo A Series', 4, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (18, N'Samsung X Fold Series', 2, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (19, N'Samsung I Series', 2, 1)
INSERT [dbo].[DongSanPham] ([ID], [Ten], [IdHang], [TrangThai]) VALUES (20, N'Xiaomi Redmi note 7', 1, 1)
SET IDENTITY_INSERT [dbo].[DongSanPham] OFF
GO
SET IDENTITY_INSERT [dbo].[HangDienThoai] ON 

INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (1, N'Apple', 1)
INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (2, N'Samsung', 1)
INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (3, N'Xiaomi', 0)
INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (4, N'Oppo', 1)
INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (5, N'Nokia', 0)
INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (6, N'Realme', 1)
INSERT [dbo].[HangDienThoai] ([ID], [Ten], [TrangThai]) VALUES (7, N'Xiaomii', 1)
SET IDENTITY_INSERT [dbo].[HangDienThoai] OFF
GO
SET IDENTITY_INSERT [dbo].[HeDieuHanh] ON 

INSERT [dbo].[HeDieuHanh] ([ID], [Ten], [TrangThai]) VALUES (1, N'', 1)
INSERT [dbo].[HeDieuHanh] ([ID], [Ten], [TrangThai]) VALUES (2, N'Android', 1)
INSERT [dbo].[HeDieuHanh] ([ID], [Ten], [TrangThai]) VALUES (4, N'BlackBerry OS', 1)
INSERT [dbo].[HeDieuHanh] ([ID], [Ten], [TrangThai]) VALUES (5, N'He dieu hanh b', 0)
INSERT [dbo].[HeDieuHanh] ([ID], [Ten], [TrangThai]) VALUES (6, N'He dieu hanh aa', 0)
INSERT [dbo].[HeDieuHanh] ([ID], [Ten], [TrangThai]) VALUES (7, N'he dieu hanh cc', 0)
SET IDENTITY_INSERT [dbo].[HeDieuHanh] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (2, CAST(N'2023-04-02T00:19:35.293' AS DateTime), CAST(N'2023-04-02T00:19:35.293' AS DateTime), 3898000, 64970000, 61100000, 28000, 0, 0, 0, N'1', 1, 1, 1, N'HD20230402T001935', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (3, CAST(N'2023-04-02T00:22:33.357' AS DateTime), CAST(N'2023-04-02T00:22:33.357' AS DateTime), 749000, 29990000, 29300000, 59000, 0, 0, 0, N'1', 1, 1, 1, N'3', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (4, CAST(N'2023-04-02T00:23:21.450' AS DateTime), CAST(N'2023-04-02T00:23:21.450' AS DateTime), 2099000, 34990000, 32900000, 9000, 0, 0, 0, N'1', 1, 1, 1, N'4', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (5, CAST(N'2023-04-02T11:30:31.853' AS DateTime), CAST(N'2023-04-02T11:30:31.853' AS DateTime), 2294000, 50980000, 49000000, 314000, 0, 0, 0, N'1', 1, 1, 1, N'5', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (6, CAST(N'2023-04-02T11:32:18.733' AS DateTime), CAST(N'2023-04-02T11:32:18.733' AS DateTime), 674000, 14990000, 14500000, 184000, 0, 0, 0, N'1', 1, 1, 1, N'6', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (7, CAST(N'2023-04-02T13:24:25.160' AS DateTime), CAST(N'2023-04-02T13:24:25.160' AS DateTime), 4918000, 81970000, 77100000, 48000, 0, 0, 0, N'1', 1, 1, 5, N'7', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (10, CAST(N'2023-04-05T23:29:52.297' AS DateTime), CAST(N'2023-04-05T23:29:52.297' AS DateTime), 1544000, 24990000, 23500000, 54000, 0, 0, 0, N'0', 1, 2, 4, N'10', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (11, CAST(N'2023-04-05T23:38:45.350' AS DateTime), CAST(N'2023-04-05T23:38:45.350' AS DateTime), 2858000, 39980000, 37200000, 78000, 0, 0, 0, N'0', 1, 2, 5, N'11', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (12, CAST(N'2023-04-06T01:21:00.390' AS DateTime), CAST(N'2023-04-06T01:21:00.390' AS DateTime), 2489000, 34990000, 0, 0, 1, 17000000, 15501000, N'0', 1, 1, 1, N'12', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (13, CAST(N'2023-04-06T01:34:42.500' AS DateTime), CAST(N'2023-04-06T01:34:42.500' AS DateTime), 3298000, 49980000, 0, 0, 1, 24000000, 22682000, N'0', 1, 1, 5, N'13', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (14, CAST(N'2023-04-06T22:20:28.483' AS DateTime), CAST(N'2023-04-06T22:20:28.483' AS DateTime), 5188000, 79970000, 74800000, 18000, 0, 0, 0, N'0', NULL, 3, 5, N'HD20230406T222028', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (15, CAST(N'2023-04-06T23:11:06.087' AS DateTime), CAST(N'2023-04-06T23:11:06.087' AS DateTime), 3658000, 60980000, 0, 0, 1, 30000000, 27322000, N'0', NULL, 2, 5, N'HD20230406T231106', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (16, CAST(N'2023-04-06T23:36:50.943' AS DateTime), CAST(N'2023-04-06T23:36:50.943' AS DateTime), 3598000, 59980000, 0, 0, 1, 32000000, 24382000, N'0', NULL, 3, 5, N'HD20230406T233650', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (17, CAST(N'2023-04-07T10:22:18.200' AS DateTime), CAST(N'2023-04-07T10:22:18.200' AS DateTime), 2099000, 34990000, 35000000, 2109000, 0, 0, 0, N'0', NULL, 1, 5, N'HD20230407T102218', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (18, CAST(N'2023-04-07T16:20:09.453' AS DateTime), CAST(N'2023-04-07T16:20:09.453' AS DateTime), 2099000, 34990000, 0, 0, 1, 18888888, 14002112, N'0', NULL, 3, 5, N'HD20230407T162009', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (19, CAST(N'2023-04-11T09:20:51.417' AS DateTime), CAST(N'2023-04-11T09:20:51.417' AS DateTime), 2458000, 40980000, 39000000, 478000, 0, 0, 0, N'1', 2, 3, 5, N'HD20230411T092051', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (20, CAST(N'2023-04-11T09:22:27.983' AS DateTime), CAST(N'2023-04-11T09:22:27.983' AS DateTime), 674000, 14990000, 15000000, 684000, 0, 0, 0, N'1', 2, 3, 4, N'HD20230411T092227', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (21, CAST(N'2023-04-11T09:25:47.207' AS DateTime), CAST(N'2023-04-11T09:25:47.207' AS DateTime), 2699000, 34990000, 35000000, 2709000, 0, 0, 0, N'1', 2, 2, 5, N'HD20230411T092547', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (22, CAST(N'2023-04-11T11:30:00.840' AS DateTime), CAST(N'2023-04-11T11:30:00.840' AS DateTime), 4242000, 14990000, 15000000, 4252000, 0, 0, 0, N'1', 2, 3, 4, N'HD20230411T113000', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (23, CAST(N'2023-04-11T11:46:34.773' AS DateTime), CAST(N'2023-04-11T11:46:34.773' AS DateTime), 888000, 14990000, 0, 0, 1, 8051000, 6051000, N'1', 2, 3, 4, N'HD20230411T114634', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (24, CAST(N'2023-04-16T10:24:25.470' AS DateTime), CAST(N'2023-04-16T10:24:25.470' AS DateTime), 2158000, 35970000, 339000000, 305188000, 0, 0, 0, N'1', 2, 3, 5, N'HD20230416T102425', NULL)
INSERT [dbo].[HoaDon] ([ID], [NgayTao], [NgayThanhToan], [TienGiam], [TongTien], [TienKhachDua], [TienThua], [TraGop], [TienTraTruoc], [TienThieu], [HinhThucThanhToan], [IdNhanVien], [IdKhachHang], [IdPhieuGiamGia], [MaHoaDon], [MaGiaoDichChuyenKhoan]) VALUES (25, CAST(N'2023-04-16T10:57:21.383' AS DateTime), CAST(N'2023-04-16T10:57:21.383' AS DateTime), 1169000, 25990000, 0, 0, 1, 13000000, 11821000, N'1', 2, 2, 4, N'HD20230416T105721', NULL)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDonChiTiet] ON 

INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (3, 2, 6)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (4, 2, 95)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (5, 2, 96)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (6, 3, 1)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (7, 4, 7)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (8, 5, 46)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (9, 5, 97)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (10, 6, 106)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (11, 7, 44)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (12, 7, 45)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (13, 7, 2)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (17, 10, 98)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (18, 11, 137)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (19, 11, 99)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (20, 12, 133)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (21, 13, 138)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (22, 13, 134)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (23, 14, 3)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (24, 14, 135)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (25, 14, 139)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (26, 15, 43)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (27, 15, 136)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (28, 16, 151)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (29, 16, 100)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (30, 17, 152)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (31, 18, 153)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (32, 19, 47)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (33, 19, 142)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (34, 20, 143)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (35, 21, 155)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (36, 22, 144)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (37, 23, 145)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (38, 24, 66)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (39, 24, 81)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IdHoaDon], [IdImei]) VALUES (40, 25, 67)
SET IDENTITY_INSERT [dbo].[HoaDonChiTiet] OFF
GO
SET IDENTITY_INSERT [dbo].[IMEI] ON 

INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (1, N'111111111122222', 1, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (2, N'222222222211111', 1, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (3, N'198767725109801', 1, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (4, N'096700762418920', 1, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (5, N'078002987182928', 1, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (6, N'209312301239123', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (7, N'091291239123992', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (43, N'111111111122223', 6, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (44, N'111111111122224', 6, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (45, N'111111111122225', 6, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (46, N'111111111122226', 7, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (47, N'111111111122227', 7, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (66, N'111111111122228', 7, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (67, N'111111111122229', 7, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (68, N'111111111122231', 6, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (80, N'111111111122232', 8, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (81, N'111111111122233', 8, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (87, N'111111111122234', 8, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (88, N'111111111122235', 8, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (90, N'111111111122236', 8, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (95, N'111111111122237', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (96, N'111111111122238', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (97, N'111111111122239', 11, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (98, N'111111111122240', 11, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (99, N'111111111122241', 11, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (100, N'111111111122242', 11, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (103, N'111111111122245', 11, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (106, N'111111111122246', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (107, N'111111111122247', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (108, N'111111111122248', 7, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (109, N'111111111122249', 6, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (110, N'111111111122250', 6, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (117, N'111111111122251', 11, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (118, N'111111111122252', 11, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (119, N'111111111122253', 11, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (120, N'111111111122254', 11, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (121, N'111111111122255', 11, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (132, N'111111111122256', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (133, N'111111111122257', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (134, N'111111111122258', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (135, N'111111111122259', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (136, N'111111111122260', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (137, N'111111111122261', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (138, N'111111111122262', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (139, N'111111111122263', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (140, N'111111111122264', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (141, N'404040404000001', 9, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (142, N'404040404000002', 9, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (143, N'404040404000003', 9, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (144, N'404040404000004', 9, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (145, N'404040404000005', 9, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (146, N'404040404000006', 9, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (147, N'404040404000007', 9, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (148, N'404040404000008', 9, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (149, N'404040404000009', 9, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (150, N'404040404000010', 9, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (151, N'404040404000011', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (152, N'404040404000012', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (153, N'404040404000013', 2, 2)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (154, N'404040404000014', 2, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (155, N'404040404000015', 2, 1)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (156, N'404040404000016', 2, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (157, N'404040404000017', 2, 0)
INSERT [dbo].[IMEI] ([ID], [IMEI], [IdDienThoai], [TrangThai]) VALUES (158, N'404040404000018', 2, 0)
SET IDENTITY_INSERT [dbo].[IMEI] OFF
GO
SET IDENTITY_INSERT [dbo].[KhachHang] ON 

INSERT [dbo].[KhachHang] ([ID], [HoTen], [Email], [Sdt], [GioiTinh], [NgaySinh], [DiaChi], [TrangThai], [IdTheTichDiem]) VALUES (1, N'Phạm Văn Bình', N'binh78@gmail.com', N'09345677212', 1, CAST(N'2003-02-12' AS Date), N'Thai Binh', 1, 1)
INSERT [dbo].[KhachHang] ([ID], [HoTen], [Email], [Sdt], [GioiTinh], [NgaySinh], [DiaChi], [TrangThai], [IdTheTichDiem]) VALUES (2, N'Nguyễn Văn An', N'an123@gmail.com', N'0934875159', 1, CAST(N'2003-12-12' AS Date), N'77 Trần Nhân Tông', 1, 2)
INSERT [dbo].[KhachHang] ([ID], [HoTen], [Email], [Sdt], [GioiTinh], [NgaySinh], [DiaChi], [TrangThai], [IdTheTichDiem]) VALUES (3, N'Nguyễn Đình Hiếu', N'hieupham09@gmail.com', N'0934129828', 1, CAST(N'1998-02-07' AS Date), N'2 Trần Nhân Tông', 1, 3)
SET IDENTITY_INSERT [dbo].[KhachHang] OFF
GO
SET IDENTITY_INSERT [dbo].[LichSuTraGop] ON 

INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (1, N'LS20230406T231108', CAST(N'2023-04-06' AS Date), 30000000, N'...', 1)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (2, N'LS20230406T233652', CAST(N'2023-04-06' AS Date), 32000000, N'...', 2)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (3, N'LS20230407T162010', CAST(N'2023-04-07' AS Date), 18888888, N'...', 3)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (4, N'LSTG4', CAST(N'2023-04-11' AS Date), 4673000, N'không', 2)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (5, N'LSTG5', CAST(N'2023-04-11' AS Date), 4673000, N'không', 2)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (6, N'LSTG6', CAST(N'2023-04-11' AS Date), 7650000, N'không', 1)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (7, N'LS20230411T114636', CAST(N'2023-04-11' AS Date), 8051000, N'...', 4)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (8, N'LSTG8', CAST(N'2023-04-11' AS Date), 6051000, N'', 4)
INSERT [dbo].[LichSuTraGop] ([ID], [MaLichSuTraGop], [NgayThanhToan], [TongTien], [GhiChu], [IdPhieuTraGop]) VALUES (9, N'LS20230416T105722', CAST(N'2023-04-16' AS Date), 13000000, N'...', 5)
SET IDENTITY_INSERT [dbo].[LichSuTraGop] OFF
GO
SET IDENTITY_INSERT [dbo].[LoaiBaoHanh] ON 

INSERT [dbo].[LoaiBaoHanh] ([ID], [TenLoaiBH], [DieuKienBaoHanh]) VALUES (5, N'Pin', N'Lỗi do NSX')
INSERT [dbo].[LoaiBaoHanh] ([ID], [TenLoaiBH], [DieuKienBaoHanh]) VALUES (6, N'Màn hình', N'Lỗi do NSX')
INSERT [dbo].[LoaiBaoHanh] ([ID], [TenLoaiBH], [DieuKienBaoHanh]) VALUES (7, N'Loa', N'Lỗi do NSX')
INSERT [dbo].[LoaiBaoHanh] ([ID], [TenLoaiBH], [DieuKienBaoHanh]) VALUES (8, N'Micro', N'Lỗi do NSX')
SET IDENTITY_INSERT [dbo].[LoaiBaoHanh] OFF
GO
SET IDENTITY_INSERT [dbo].[ManHinhChiTiet] ON 

INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (1, CAST(6.8 AS Decimal(2, 1)), N'3600x1200', N'SUPER_AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (2, CAST(6.8 AS Decimal(2, 1)), N'1440x3088', N'AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (4, CAST(6.8 AS Decimal(2, 1)), N'1440 x 3088', N'DYNAMIC_AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (6, CAST(6.8 AS Decimal(2, 1)), N'1440 x 3088', N'DYNAMIC_AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (7, CAST(6.8 AS Decimal(2, 1)), N'1440 x 3088', N'DYNAMIC_AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (8, CAST(6.7 AS Decimal(2, 1)), N'2778 x 1284', N'AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (9, CAST(6.8 AS Decimal(2, 1)), N'1080 x 2412', N'AMOLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (10, CAST(6.1 AS Decimal(2, 1)), N'1792 x 828', N'IPS')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (12, CAST(6.1 AS Decimal(2, 1)), N'1170 x 2532', N'OLED')
INSERT [dbo].[ManHinhChiTiet] ([ID], [KichThuocManHinh], [DoPhanGiai], [LoaiManHinh]) VALUES (13, CAST(6.8 AS Decimal(2, 1)), N'1080 x 2412', N'AMOLED')
SET IDENTITY_INSERT [dbo].[ManHinhChiTiet] OFF
GO
SET IDENTITY_INSERT [dbo].[MauSac] ON 

INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (1, N'YE12', N'Vàng', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (2, N'BL02', N'Ðen', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (3, N'BL09', N'Xanh dương', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (4, N'GR01', N'Xanh lá cây', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (5, N'PI12', N'Hồng', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (6, N'YE13', N'Vàng đậm', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (8, N'RE001', N'Đỏ', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (9, N'VI001', N'Tím', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (10, N'OR002', N'Cam nhạt', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (11, N'WH', N'Trắng', 1)
INSERT [dbo].[MauSac] ([ID], [MaMauSac], [TenMauSac], [TrangThai]) VALUES (12, N'GREY001', N'Xám', 1)
SET IDENTITY_INSERT [dbo].[MauSac] OFF
GO
SET IDENTITY_INSERT [dbo].[NhanVien] ON 

INSERT [dbo].[NhanVien] ([ID], [HoTen], [GioiTinh], [Sdt], [NgaySinh], [DiaChi], [Email], [ChucVu], [TrangThai], [HinhAnh], [IdTaiKhoan]) VALUES (1, N'Nguyễn Khắc Thịnh', 1, N'0961271232', CAST(N'2004-07-22' AS Date), N'365 Nguyễn Chí Thanh', N'thinh123@gmail.com', 0, 1, N'xyz1.png', 1)
INSERT [dbo].[NhanVien] ([ID], [HoTen], [GioiTinh], [Sdt], [NgaySinh], [DiaChi], [Email], [ChucVu], [TrangThai], [HinhAnh], [IdTaiKhoan]) VALUES (2, N'Nguyễn Thu Thảo', 1, N'0912018231223', CAST(N'2003-02-22' AS Date), N'365 Phạm Văn Đồng', N'thao321@gmail.com', 0, 1, N'xyz.png', 2)
SET IDENTITY_INSERT [dbo].[NhanVien] OFF
GO
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (1, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (1, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (1, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (2, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (2, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (2, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (3, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (3, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (3, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (5, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (5, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (5, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (7, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (7, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (7, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (4, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (4, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (4, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (6, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (6, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (6, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (8, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (8, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (8, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (9, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (9, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (9, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (10, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (10, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (10, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (11, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (11, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (14, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (14, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (14, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (15, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (15, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (15, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (16, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (16, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (16, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (17, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (17, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (18, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (18, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (18, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (11, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (12, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (12, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (12, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (13, 5)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (13, 6)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (13, 7)
INSERT [dbo].[PhieuBaoHang_LoaiBaoHanh] ([IdPhieuBaoHanh], [IdLoaiBaoHanh]) VALUES (17, 6)
GO
SET IDENTITY_INSERT [dbo].[PhieuBaoHanh] ON 

INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (1, 23, 1)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (2, 24, 2)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (3, 25, 3)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (4, 26, 4)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (5, 27, 5)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (6, 28, 6)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (7, 29, 7)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (8, 30, 8)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (9, 31, 9)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (10, 32, 10)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (11, 33, 11)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (12, 34, 12)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (13, 35, 13)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (14, 36, 14)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (15, 37, 15)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (16, 38, 16)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (17, 39, 17)
INSERT [dbo].[PhieuBaoHanh] ([ID], [IdHoaDonCT], [IdChiTietPhieuBH]) VALUES (18, 40, 18)
SET IDENTITY_INSERT [dbo].[PhieuBaoHanh] OFF
GO
SET IDENTITY_INSERT [dbo].[PhieuGiamGia] ON 

INSERT [dbo].[PhieuGiamGia] ([ID], [MaPhieu], [TenPhieu], [IdPhieuChiTiet]) VALUES (1, N'GG001-2', N'ten phieu gg 1-2', 1)
INSERT [dbo].[PhieuGiamGia] ([ID], [MaPhieu], [TenPhieu], [IdPhieuChiTiet]) VALUES (3, N'GG002', N'ten phieu gg 2', 2)
INSERT [dbo].[PhieuGiamGia] ([ID], [MaPhieu], [TenPhieu], [IdPhieuChiTiet]) VALUES (4, N'GG003', N'ten pgg 4', 3)
INSERT [dbo].[PhieuGiamGia] ([ID], [MaPhieu], [TenPhieu], [IdPhieuChiTiet]) VALUES (5, N'GG004', N'ten pgg 5', 4)
SET IDENTITY_INSERT [dbo].[PhieuGiamGia] OFF
GO
SET IDENTITY_INSERT [dbo].[PhieuGiamGiaChiTiet] ON 

INSERT [dbo].[PhieuGiamGiaChiTiet] ([ID], [NgayBatDau], [NgayKetThuc], [LuotSuDung], [DienKien], [GiaTri], [TrangThai]) VALUES (1, CAST(N'2023-05-11' AS Date), CAST(N'2023-05-16' AS Date), 150, 15000000, 3.5, 2)
INSERT [dbo].[PhieuGiamGiaChiTiet] ([ID], [NgayBatDau], [NgayKetThuc], [LuotSuDung], [DienKien], [GiaTri], [TrangThai]) VALUES (2, CAST(N'2023-02-10' AS Date), CAST(N'2023-04-15' AS Date), 23, 9000000, 2.5, 1)
INSERT [dbo].[PhieuGiamGiaChiTiet] ([ID], [NgayBatDau], [NgayKetThuc], [LuotSuDung], [DienKien], [GiaTri], [TrangThai]) VALUES (3, CAST(N'2023-04-01' AS Date), CAST(N'2023-05-01' AS Date), 93, 10000000, 4.5, 0)
INSERT [dbo].[PhieuGiamGiaChiTiet] ([ID], [NgayBatDau], [NgayKetThuc], [LuotSuDung], [DienKien], [GiaTri], [TrangThai]) VALUES (4, CAST(N'2023-04-01' AS Date), CAST(N'2023-05-01' AS Date), 99, 30000000, 6, 0)
SET IDENTITY_INSERT [dbo].[PhieuGiamGiaChiTiet] OFF
GO
SET IDENTITY_INSERT [dbo].[PhieuTraGop] ON 

INSERT [dbo].[PhieuTraGop] ([ID], [MaPhieu], [TongPhaiTra], [LaiSuat], [KyHan], [NgayTao], [PhaiTra], [TrangThai], [IdHoaDon]) VALUES (1, N'PTG1', 57322000, 12, 4, CAST(N'2023-04-06' AS Date), 7650000, 1, 15)
INSERT [dbo].[PhieuTraGop] ([ID], [MaPhieu], [TongPhaiTra], [LaiSuat], [KyHan], [NgayTao], [PhaiTra], [TrangThai], [IdHoaDon]) VALUES (2, N'PTG2', 56382000, 15, 6, CAST(N'2023-04-06' AS Date), 4673000, 0, 16)
INSERT [dbo].[PhieuTraGop] ([ID], [MaPhieu], [TongPhaiTra], [LaiSuat], [KyHan], [NgayTao], [PhaiTra], [TrangThai], [IdHoaDon]) VALUES (3, N'PTG3', 32891000, 5, 4, CAST(N'2023-04-07' AS Date), 3675000, 0, 18)
INSERT [dbo].[PhieuTraGop] ([ID], [MaPhieu], [TongPhaiTra], [LaiSuat], [KyHan], [NgayTao], [PhaiTra], [TrangThai], [IdHoaDon]) VALUES (4, N'PTG4', 14102000, 5, 4, CAST(N'2023-04-11' AS Date), 1588000, 1, 23)
INSERT [dbo].[PhieuTraGop] ([ID], [MaPhieu], [TongPhaiTra], [LaiSuat], [KyHan], [NgayTao], [PhaiTra], [TrangThai], [IdHoaDon]) VALUES (5, N'PTG5', 24821000, 8, 4, CAST(N'2023-04-16' AS Date), 3191000, 0, 25)
SET IDENTITY_INSERT [dbo].[PhieuTraGop] OFF
GO
SET IDENTITY_INSERT [dbo].[TaiKhoan] ON 

INSERT [dbo].[TaiKhoan] ([ID], [TaiKhoan], [MatKhau]) VALUES (1, N'thingnguyen1234', N'231')
INSERT [dbo].[TaiKhoan] ([ID], [TaiKhoan], [MatKhau]) VALUES (2, N'1', N'1')
SET IDENTITY_INSERT [dbo].[TaiKhoan] OFF
GO
SET IDENTITY_INSERT [dbo].[TheTichDiem] ON 

INSERT [dbo].[TheTichDiem] ([ID], [MaThe], [NgayKichHoat], [SoDiem], [TrangThai]) VALUES (1, N'TTD001', CAST(N'2023-03-21' AS Date), 657, 1)
INSERT [dbo].[TheTichDiem] ([ID], [MaThe], [NgayKichHoat], [SoDiem], [TrangThai]) VALUES (2, N'9892 0232 9719', CAST(N'2023-03-21' AS Date), 260, 1)
INSERT [dbo].[TheTichDiem] ([ID], [MaThe], [NgayKichHoat], [SoDiem], [TrangThai]) VALUES (3, N'9082 1109 2376', CAST(N'2023-03-21' AS Date), 837, 1)
SET IDENTITY_INSERT [dbo].[TheTichDiem] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DienThoa__27258654B5F9C921]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[DienThoai] ADD UNIQUE NONCLUSTERED 
(
	[MaDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__DongSanP__C451FA830BDF0466]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[DongSanPham] ADD UNIQUE NONCLUSTERED 
(
	[Ten] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HangDien__C451FA83B6D3ADAC]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[HangDienThoai] ADD UNIQUE NONCLUSTERED 
(
	[Ten] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HeDieuHa__C451FA830C6F3FC9]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[HeDieuHanh] ADD UNIQUE NONCLUSTERED 
(
	[Ten] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__HoaDon__835ED13A9DE3678A]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[HoaDon] ADD UNIQUE NONCLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__IMEI__8DF371FD94EE67D4]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[IMEI] ADD UNIQUE NONCLUSTERED 
(
	[IMEI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__LichSuTr__79D43E27ECC7AD58]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[LichSuTraGop] ADD UNIQUE NONCLUSTERED 
(
	[MaLichSuTraGop] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__LoaiBaoH__F43541AC358BA6A0]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[LoaiBaoHanh] ADD UNIQUE NONCLUSTERED 
(
	[TenLoaiBH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__MauSac__B9A91163D3254B4C]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[MauSac] ADD UNIQUE NONCLUSTERED 
(
	[MaMauSac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__PhieuTra__2660BFE1CA4B2625]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[PhieuTraGop] ADD UNIQUE NONCLUSTERED 
(
	[MaPhieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__TaiKhoan__D5B8C7F0D3F3B3F1]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[TaiKhoan] ADD UNIQUE NONCLUSTERED 
(
	[TaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__TheTichD__314EEAAEE8E59347]    Script Date: 30/10/2023 2:22:37 CH ******/
ALTER TABLE [dbo].[TheTichDiem] ADD UNIQUE NONCLUSTERED 
(
	[MaThe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[DienThoai]  WITH CHECK ADD FOREIGN KEY([IdCameraChiTiet])
REFERENCES [dbo].[CameraChiTiet] ([ID])
GO
ALTER TABLE [dbo].[DienThoai]  WITH CHECK ADD FOREIGN KEY([IdDongSanPham])
REFERENCES [dbo].[DongSanPham] ([ID])
GO
ALTER TABLE [dbo].[DienThoai]  WITH CHECK ADD FOREIGN KEY([IdHang])
REFERENCES [dbo].[HangDienThoai] ([ID])
GO
ALTER TABLE [dbo].[DienThoai]  WITH CHECK ADD FOREIGN KEY([IdHeDieuHanh])
REFERENCES [dbo].[HeDieuHanh] ([ID])
GO
ALTER TABLE [dbo].[DienThoai]  WITH CHECK ADD FOREIGN KEY([IdManHinhChiTiet])
REFERENCES [dbo].[ManHinhChiTiet] ([ID])
GO
ALTER TABLE [dbo].[DienThoai]  WITH CHECK ADD FOREIGN KEY([IdMauSac])
REFERENCES [dbo].[MauSac] ([ID])
GO
ALTER TABLE [dbo].[DongSanPham]  WITH CHECK ADD FOREIGN KEY([IdHang])
REFERENCES [dbo].[HangDienThoai] ([ID])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdKhachHang])
REFERENCES [dbo].[KhachHang] ([ID])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdNhanVien])
REFERENCES [dbo].[NhanVien] ([ID])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([IdPhieuGiamGia])
REFERENCES [dbo].[PhieuGiamGia] ([ID])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([IdHoaDon])
REFERENCES [dbo].[HoaDon] ([ID])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([IdImei])
REFERENCES [dbo].[IMEI] ([ID])
GO
ALTER TABLE [dbo].[IMEI]  WITH CHECK ADD FOREIGN KEY([IdDienThoai])
REFERENCES [dbo].[DienThoai] ([ID])
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD FOREIGN KEY([IdTheTichDiem])
REFERENCES [dbo].[TheTichDiem] ([ID])
GO
ALTER TABLE [dbo].[LichSuTraGop]  WITH CHECK ADD FOREIGN KEY([IdPhieuTraGop])
REFERENCES [dbo].[PhieuTraGop] ([ID])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([IdTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([ID])
GO
ALTER TABLE [dbo].[PhieuBaoHang_LoaiBaoHanh]  WITH CHECK ADD FOREIGN KEY([IdLoaiBaoHanh])
REFERENCES [dbo].[LoaiBaoHanh] ([ID])
GO
ALTER TABLE [dbo].[PhieuBaoHang_LoaiBaoHanh]  WITH CHECK ADD FOREIGN KEY([IdPhieuBaoHanh])
REFERENCES [dbo].[PhieuBaoHanh] ([ID])
GO
ALTER TABLE [dbo].[PhieuBaoHanh]  WITH CHECK ADD FOREIGN KEY([IdChiTietPhieuBH])
REFERENCES [dbo].[ChiTietPhieuBaoHanh] ([ID])
GO
ALTER TABLE [dbo].[PhieuBaoHanh]  WITH CHECK ADD FOREIGN KEY([IdHoaDonCT])
REFERENCES [dbo].[HoaDonChiTiet] ([ID])
GO
ALTER TABLE [dbo].[PhieuGiamGia]  WITH CHECK ADD FOREIGN KEY([IdPhieuChiTiet])
REFERENCES [dbo].[PhieuGiamGiaChiTiet] ([ID])
GO
ALTER TABLE [dbo].[PhieuTraGop]  WITH CHECK ADD FOREIGN KEY([IdHoaDon])
REFERENCES [dbo].[HoaDon] ([ID])
GO
USE [master]
GO
ALTER DATABASE [duan1_spring2023] SET  READ_WRITE 
GO
